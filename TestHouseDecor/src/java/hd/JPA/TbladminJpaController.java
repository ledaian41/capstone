/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tbladmin;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Lê Đại An
 */
public class TbladminJpaController implements Serializable {

    public TbladminJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbladmin tbladmin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tbladmin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbladmin tbladmin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tbladmin = em.merge(tbladmin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbladmin.getId();
                if (findTbladmin(id) == null) {
                    throw new NonexistentEntityException("The tbladmin with id " + id + " no longer exists.");
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
            Tbladmin tbladmin;
            try {
                tbladmin = em.getReference(Tbladmin.class, id);
                tbladmin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbladmin with id " + id + " no longer exists.", enfe);
            }
            em.remove(tbladmin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbladmin> findTbladminEntities() {
        return findTbladminEntities(true, -1, -1);
    }

    public List<Tbladmin> findTbladminEntities(int maxResults, int firstResult) {
        return findTbladminEntities(false, maxResults, firstResult);
    }

    private List<Tbladmin> findTbladminEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbladmin.class));
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

    public Tbladmin findTbladmin(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbladmin.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbladminCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbladmin> rt = cq.from(Tbladmin.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public boolean checkLogin(String username, String password) {
        boolean flag = false;
        EntityManager em = getEntityManager();
        try {
            String jsql = "SELECT t.id FROM Tbladmin t WHERE t.email = :email AND t.password = :password";
            Query query = em.createQuery(jsql);
            query.setParameter("email", username);
            query.setParameter("password", password);
            if (!query.getResultList().isEmpty()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return flag;
    }
}
