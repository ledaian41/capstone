/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tblrole;
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
public class TblroleJpaController implements Serializable {

    public TblroleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblrole tblrole) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbluser userID = tblrole.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                tblrole.setUserID(userID);
            }
            em.persist(tblrole);
            if (userID != null) {
                userID.getTblroleCollection().add(tblrole);
                userID = em.merge(userID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblrole tblrole) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblrole persistentTblrole = em.find(Tblrole.class, tblrole.getRoleID());
            Tbluser userIDOld = persistentTblrole.getUserID();
            Tbluser userIDNew = tblrole.getUserID();
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                tblrole.setUserID(userIDNew);
            }
            tblrole = em.merge(tblrole);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getTblroleCollection().remove(tblrole);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getTblroleCollection().add(tblrole);
                userIDNew = em.merge(userIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblrole.getRoleID();
                if (findTblrole(id) == null) {
                    throw new NonexistentEntityException("The tblrole with id " + id + " no longer exists.");
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
            Tblrole tblrole;
            try {
                tblrole = em.getReference(Tblrole.class, id);
                tblrole.getRoleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblrole with id " + id + " no longer exists.", enfe);
            }
            Tbluser userID = tblrole.getUserID();
            if (userID != null) {
                userID.getTblroleCollection().remove(tblrole);
                userID = em.merge(userID);
            }
            em.remove(tblrole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblrole> findTblroleEntities() {
        return findTblroleEntities(true, -1, -1);
    }

    public List<Tblrole> findTblroleEntities(int maxResults, int firstResult) {
        return findTblroleEntities(false, maxResults, firstResult);
    }

    private List<Tblrole> findTblroleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblrole.class));
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

    public Tblrole findTblrole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblrole.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblroleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblrole> rt = cq.from(Tblrole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
