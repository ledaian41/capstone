/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tblstory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbluser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblstoryJpaController implements Serializable {

    public TblstoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblstory tblstory) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbluser userID = tblstory.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                tblstory.setUserID(userID);
            }
            em.persist(tblstory);
            if (userID != null) {
                userID.getTblstoryCollection().add(tblstory);
                userID = em.merge(userID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblstory tblstory) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblstory persistentTblstory = em.find(Tblstory.class, tblstory.getStoryID());
            Tbluser userIDOld = persistentTblstory.getUserID();
            Tbluser userIDNew = tblstory.getUserID();
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                tblstory.setUserID(userIDNew);
            }
            tblstory = em.merge(tblstory);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getTblstoryCollection().remove(tblstory);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getTblstoryCollection().add(tblstory);
                userIDNew = em.merge(userIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblstory.getStoryID();
                if (findTblstory(id) == null) {
                    throw new NonexistentEntityException("The tblstory with id " + id + " no longer exists.");
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
            Tblstory tblstory;
            try {
                tblstory = em.getReference(Tblstory.class, id);
                tblstory.getStoryID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblstory with id " + id + " no longer exists.", enfe);
            }
            Tbluser userID = tblstory.getUserID();
            if (userID != null) {
                userID.getTblstoryCollection().remove(tblstory);
                userID = em.merge(userID);
            }
            em.remove(tblstory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblstory> findTblstoryEntities() {
        return findTblstoryEntities(true, -1, -1);
    }

    public List<Tblstory> findTblstoryEntities(int maxResults, int firstResult) {
        return findTblstoryEntities(false, maxResults, firstResult);
    }

    private List<Tblstory> findTblstoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblstory.class));
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

    public Tblstory findTblstory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblstory.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblstoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblstory> rt = cq.from(Tblstory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
