/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.IllegalOrphanException;
import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tblproduct;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tblseller;
import hd.entity.Tblpromotion;
import java.util.ArrayList;
import java.util.Collection;
import hd.entity.Tblproductphoto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblproductJpaController implements Serializable {

    public TblproductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblproduct tblproduct) {
        if (tblproduct.getTblpromotionCollection() == null) {
            tblproduct.setTblpromotionCollection(new ArrayList<Tblpromotion>());
        }
        if (tblproduct.getTblproductphotoCollection() == null) {
            tblproduct.setTblproductphotoCollection(new ArrayList<Tblproductphoto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblseller tblSelleruserID = tblproduct.getTblSelleruserID();
            if (tblSelleruserID != null) {
                tblSelleruserID = em.getReference(tblSelleruserID.getClass(), tblSelleruserID.getUserID());
                tblproduct.setTblSelleruserID(tblSelleruserID);
            }
            Collection<Tblpromotion> attachedTblpromotionCollection = new ArrayList<Tblpromotion>();
            for (Tblpromotion tblpromotionCollectionTblpromotionToAttach : tblproduct.getTblpromotionCollection()) {
                tblpromotionCollectionTblpromotionToAttach = em.getReference(tblpromotionCollectionTblpromotionToAttach.getClass(), tblpromotionCollectionTblpromotionToAttach.getId());
                attachedTblpromotionCollection.add(tblpromotionCollectionTblpromotionToAttach);
            }
            tblproduct.setTblpromotionCollection(attachedTblpromotionCollection);
            Collection<Tblproductphoto> attachedTblproductphotoCollection = new ArrayList<Tblproductphoto>();
            for (Tblproductphoto tblproductphotoCollectionTblproductphotoToAttach : tblproduct.getTblproductphotoCollection()) {
                tblproductphotoCollectionTblproductphotoToAttach = em.getReference(tblproductphotoCollectionTblproductphotoToAttach.getClass(), tblproductphotoCollectionTblproductphotoToAttach.getProductPhotoID());
                attachedTblproductphotoCollection.add(tblproductphotoCollectionTblproductphotoToAttach);
            }
            tblproduct.setTblproductphotoCollection(attachedTblproductphotoCollection);
            em.persist(tblproduct);
            if (tblSelleruserID != null) {
                tblSelleruserID.getTblproductCollection().add(tblproduct);
                tblSelleruserID = em.merge(tblSelleruserID);
            }
            for (Tblpromotion tblpromotionCollectionTblpromotion : tblproduct.getTblpromotionCollection()) {
                tblpromotionCollectionTblpromotion.getTblproductCollection().add(tblproduct);
                tblpromotionCollectionTblpromotion = em.merge(tblpromotionCollectionTblpromotion);
            }
            for (Tblproductphoto tblproductphotoCollectionTblproductphoto : tblproduct.getTblproductphotoCollection()) {
                Tblproduct oldTblProductproductIDOfTblproductphotoCollectionTblproductphoto = tblproductphotoCollectionTblproductphoto.getTblProductproductID();
                tblproductphotoCollectionTblproductphoto.setTblProductproductID(tblproduct);
                tblproductphotoCollectionTblproductphoto = em.merge(tblproductphotoCollectionTblproductphoto);
                if (oldTblProductproductIDOfTblproductphotoCollectionTblproductphoto != null) {
                    oldTblProductproductIDOfTblproductphotoCollectionTblproductphoto.getTblproductphotoCollection().remove(tblproductphotoCollectionTblproductphoto);
                    oldTblProductproductIDOfTblproductphotoCollectionTblproductphoto = em.merge(oldTblProductproductIDOfTblproductphotoCollectionTblproductphoto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblproduct tblproduct) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblproduct persistentTblproduct = em.find(Tblproduct.class, tblproduct.getProductID());
            Tblseller tblSelleruserIDOld = persistentTblproduct.getTblSelleruserID();
            Tblseller tblSelleruserIDNew = tblproduct.getTblSelleruserID();
            Collection<Tblpromotion> tblpromotionCollectionOld = persistentTblproduct.getTblpromotionCollection();
            Collection<Tblpromotion> tblpromotionCollectionNew = tblproduct.getTblpromotionCollection();
            Collection<Tblproductphoto> tblproductphotoCollectionOld = persistentTblproduct.getTblproductphotoCollection();
            Collection<Tblproductphoto> tblproductphotoCollectionNew = tblproduct.getTblproductphotoCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblproductphoto tblproductphotoCollectionOldTblproductphoto : tblproductphotoCollectionOld) {
                if (!tblproductphotoCollectionNew.contains(tblproductphotoCollectionOldTblproductphoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblproductphoto " + tblproductphotoCollectionOldTblproductphoto + " since its tblProductproductID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tblSelleruserIDNew != null) {
                tblSelleruserIDNew = em.getReference(tblSelleruserIDNew.getClass(), tblSelleruserIDNew.getUserID());
                tblproduct.setTblSelleruserID(tblSelleruserIDNew);
            }
            Collection<Tblpromotion> attachedTblpromotionCollectionNew = new ArrayList<Tblpromotion>();
            for (Tblpromotion tblpromotionCollectionNewTblpromotionToAttach : tblpromotionCollectionNew) {
                tblpromotionCollectionNewTblpromotionToAttach = em.getReference(tblpromotionCollectionNewTblpromotionToAttach.getClass(), tblpromotionCollectionNewTblpromotionToAttach.getId());
                attachedTblpromotionCollectionNew.add(tblpromotionCollectionNewTblpromotionToAttach);
            }
            tblpromotionCollectionNew = attachedTblpromotionCollectionNew;
            tblproduct.setTblpromotionCollection(tblpromotionCollectionNew);
            Collection<Tblproductphoto> attachedTblproductphotoCollectionNew = new ArrayList<Tblproductphoto>();
            for (Tblproductphoto tblproductphotoCollectionNewTblproductphotoToAttach : tblproductphotoCollectionNew) {
                tblproductphotoCollectionNewTblproductphotoToAttach = em.getReference(tblproductphotoCollectionNewTblproductphotoToAttach.getClass(), tblproductphotoCollectionNewTblproductphotoToAttach.getProductPhotoID());
                attachedTblproductphotoCollectionNew.add(tblproductphotoCollectionNewTblproductphotoToAttach);
            }
            tblproductphotoCollectionNew = attachedTblproductphotoCollectionNew;
            tblproduct.setTblproductphotoCollection(tblproductphotoCollectionNew);
            tblproduct = em.merge(tblproduct);
            if (tblSelleruserIDOld != null && !tblSelleruserIDOld.equals(tblSelleruserIDNew)) {
                tblSelleruserIDOld.getTblproductCollection().remove(tblproduct);
                tblSelleruserIDOld = em.merge(tblSelleruserIDOld);
            }
            if (tblSelleruserIDNew != null && !tblSelleruserIDNew.equals(tblSelleruserIDOld)) {
                tblSelleruserIDNew.getTblproductCollection().add(tblproduct);
                tblSelleruserIDNew = em.merge(tblSelleruserIDNew);
            }
            for (Tblpromotion tblpromotionCollectionOldTblpromotion : tblpromotionCollectionOld) {
                if (!tblpromotionCollectionNew.contains(tblpromotionCollectionOldTblpromotion)) {
                    tblpromotionCollectionOldTblpromotion.getTblproductCollection().remove(tblproduct);
                    tblpromotionCollectionOldTblpromotion = em.merge(tblpromotionCollectionOldTblpromotion);
                }
            }
            for (Tblpromotion tblpromotionCollectionNewTblpromotion : tblpromotionCollectionNew) {
                if (!tblpromotionCollectionOld.contains(tblpromotionCollectionNewTblpromotion)) {
                    tblpromotionCollectionNewTblpromotion.getTblproductCollection().add(tblproduct);
                    tblpromotionCollectionNewTblpromotion = em.merge(tblpromotionCollectionNewTblpromotion);
                }
            }
            for (Tblproductphoto tblproductphotoCollectionNewTblproductphoto : tblproductphotoCollectionNew) {
                if (!tblproductphotoCollectionOld.contains(tblproductphotoCollectionNewTblproductphoto)) {
                    Tblproduct oldTblProductproductIDOfTblproductphotoCollectionNewTblproductphoto = tblproductphotoCollectionNewTblproductphoto.getTblProductproductID();
                    tblproductphotoCollectionNewTblproductphoto.setTblProductproductID(tblproduct);
                    tblproductphotoCollectionNewTblproductphoto = em.merge(tblproductphotoCollectionNewTblproductphoto);
                    if (oldTblProductproductIDOfTblproductphotoCollectionNewTblproductphoto != null && !oldTblProductproductIDOfTblproductphotoCollectionNewTblproductphoto.equals(tblproduct)) {
                        oldTblProductproductIDOfTblproductphotoCollectionNewTblproductphoto.getTblproductphotoCollection().remove(tblproductphotoCollectionNewTblproductphoto);
                        oldTblProductproductIDOfTblproductphotoCollectionNewTblproductphoto = em.merge(oldTblProductproductIDOfTblproductphotoCollectionNewTblproductphoto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblproduct.getProductID();
                if (findTblproduct(id) == null) {
                    throw new NonexistentEntityException("The tblproduct with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblproduct tblproduct;
            try {
                tblproduct = em.getReference(Tblproduct.class, id);
                tblproduct.getProductID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblproduct with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblproductphoto> tblproductphotoCollectionOrphanCheck = tblproduct.getTblproductphotoCollection();
            for (Tblproductphoto tblproductphotoCollectionOrphanCheckTblproductphoto : tblproductphotoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblproduct (" + tblproduct + ") cannot be destroyed since the Tblproductphoto " + tblproductphotoCollectionOrphanCheckTblproductphoto + " in its tblproductphotoCollection field has a non-nullable tblProductproductID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tblseller tblSelleruserID = tblproduct.getTblSelleruserID();
            if (tblSelleruserID != null) {
                tblSelleruserID.getTblproductCollection().remove(tblproduct);
                tblSelleruserID = em.merge(tblSelleruserID);
            }
            Collection<Tblpromotion> tblpromotionCollection = tblproduct.getTblpromotionCollection();
            for (Tblpromotion tblpromotionCollectionTblpromotion : tblpromotionCollection) {
                tblpromotionCollectionTblpromotion.getTblproductCollection().remove(tblproduct);
                tblpromotionCollectionTblpromotion = em.merge(tblpromotionCollectionTblpromotion);
            }
            em.remove(tblproduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblproduct> findTblproductEntities() {
        return findTblproductEntities(true, -1, -1);
    }

    public List<Tblproduct> findTblproductEntities(int maxResults, int firstResult) {
        return findTblproductEntities(false, maxResults, firstResult);
    }

    private List<Tblproduct> findTblproductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblproduct.class));
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

    public Tblproduct findTblproduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblproduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblproductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblproduct> rt = cq.from(Tblproduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
