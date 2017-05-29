/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tblproduct;
import hd.entity.Tblpromotion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblpromotionJpaController implements Serializable {

    public TblpromotionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblpromotion tblpromotion) {
        if (tblpromotion.getTblproductCollection() == null) {
            tblpromotion.setTblproductCollection(new ArrayList<Tblproduct>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblproduct> attachedTblproductCollection = new ArrayList<Tblproduct>();
            for (Tblproduct tblproductCollectionTblproductToAttach : tblpromotion.getTblproductCollection()) {
                tblproductCollectionTblproductToAttach = em.getReference(tblproductCollectionTblproductToAttach.getClass(), tblproductCollectionTblproductToAttach.getProductID());
                attachedTblproductCollection.add(tblproductCollectionTblproductToAttach);
            }
            tblpromotion.setTblproductCollection(attachedTblproductCollection);
            em.persist(tblpromotion);
            for (Tblproduct tblproductCollectionTblproduct : tblpromotion.getTblproductCollection()) {
                tblproductCollectionTblproduct.getTblpromotionCollection().add(tblpromotion);
                tblproductCollectionTblproduct = em.merge(tblproductCollectionTblproduct);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblpromotion tblpromotion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblpromotion persistentTblpromotion = em.find(Tblpromotion.class, tblpromotion.getId());
            Collection<Tblproduct> tblproductCollectionOld = persistentTblpromotion.getTblproductCollection();
            Collection<Tblproduct> tblproductCollectionNew = tblpromotion.getTblproductCollection();
            Collection<Tblproduct> attachedTblproductCollectionNew = new ArrayList<Tblproduct>();
            for (Tblproduct tblproductCollectionNewTblproductToAttach : tblproductCollectionNew) {
                tblproductCollectionNewTblproductToAttach = em.getReference(tblproductCollectionNewTblproductToAttach.getClass(), tblproductCollectionNewTblproductToAttach.getProductID());
                attachedTblproductCollectionNew.add(tblproductCollectionNewTblproductToAttach);
            }
            tblproductCollectionNew = attachedTblproductCollectionNew;
            tblpromotion.setTblproductCollection(tblproductCollectionNew);
            tblpromotion = em.merge(tblpromotion);
            for (Tblproduct tblproductCollectionOldTblproduct : tblproductCollectionOld) {
                if (!tblproductCollectionNew.contains(tblproductCollectionOldTblproduct)) {
                    tblproductCollectionOldTblproduct.getTblpromotionCollection().remove(tblpromotion);
                    tblproductCollectionOldTblproduct = em.merge(tblproductCollectionOldTblproduct);
                }
            }
            for (Tblproduct tblproductCollectionNewTblproduct : tblproductCollectionNew) {
                if (!tblproductCollectionOld.contains(tblproductCollectionNewTblproduct)) {
                    tblproductCollectionNewTblproduct.getTblpromotionCollection().add(tblpromotion);
                    tblproductCollectionNewTblproduct = em.merge(tblproductCollectionNewTblproduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblpromotion.getId();
                if (findTblpromotion(id) == null) {
                    throw new NonexistentEntityException("The tblpromotion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblpromotion tblpromotion;
            try {
                tblpromotion = em.getReference(Tblpromotion.class, id);
                tblpromotion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblpromotion with id " + id + " no longer exists.", enfe);
            }
            Collection<Tblproduct> tblproductCollection = tblpromotion.getTblproductCollection();
            for (Tblproduct tblproductCollectionTblproduct : tblproductCollection) {
                tblproductCollectionTblproduct.getTblpromotionCollection().remove(tblpromotion);
                tblproductCollectionTblproduct = em.merge(tblproductCollectionTblproduct);
            }
            em.remove(tblpromotion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblpromotion> findTblpromotionEntities() {
        return findTblpromotionEntities(true, -1, -1);
    }

    public List<Tblpromotion> findTblpromotionEntities(int maxResults, int firstResult) {
        return findTblpromotionEntities(false, maxResults, firstResult);
    }

    private List<Tblpromotion> findTblpromotionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblpromotion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tblpromotion findTblpromotion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblpromotion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblpromotionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblpromotion> rt = cq.from(Tblpromotion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
