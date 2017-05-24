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
import hd.entity.Tblorder;
import hd.entity.Tblorderdetail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblorderdetailJpaController implements Serializable {

    public TblorderdetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblorderdetail tblorderdetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblorder orderID = tblorderdetail.getOrderID();
            if (orderID != null) {
                orderID = em.getReference(orderID.getClass(), orderID.getOrderID());
                tblorderdetail.setOrderID(orderID);
            }
            em.persist(tblorderdetail);
            if (orderID != null) {
                orderID.getTblorderdetailCollection().add(tblorderdetail);
                orderID = em.merge(orderID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblorderdetail tblorderdetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblorderdetail persistentTblorderdetail = em.find(Tblorderdetail.class, tblorderdetail.getOrderDetailID());
            Tblorder orderIDOld = persistentTblorderdetail.getOrderID();
            Tblorder orderIDNew = tblorderdetail.getOrderID();
            if (orderIDNew != null) {
                orderIDNew = em.getReference(orderIDNew.getClass(), orderIDNew.getOrderID());
                tblorderdetail.setOrderID(orderIDNew);
            }
            tblorderdetail = em.merge(tblorderdetail);
            if (orderIDOld != null && !orderIDOld.equals(orderIDNew)) {
                orderIDOld.getTblorderdetailCollection().remove(tblorderdetail);
                orderIDOld = em.merge(orderIDOld);
            }
            if (orderIDNew != null && !orderIDNew.equals(orderIDOld)) {
                orderIDNew.getTblorderdetailCollection().add(tblorderdetail);
                orderIDNew = em.merge(orderIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblorderdetail.getOrderDetailID();
                if (findTblorderdetail(id) == null) {
                    throw new NonexistentEntityException("The tblorderdetail with id " + id + " no longer exists.");
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
            Tblorderdetail tblorderdetail;
            try {
                tblorderdetail = em.getReference(Tblorderdetail.class, id);
                tblorderdetail.getOrderDetailID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblorderdetail with id " + id + " no longer exists.", enfe);
            }
            Tblorder orderID = tblorderdetail.getOrderID();
            if (orderID != null) {
                orderID.getTblorderdetailCollection().remove(tblorderdetail);
                orderID = em.merge(orderID);
            }
            em.remove(tblorderdetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblorderdetail> findTblorderdetailEntities() {
        return findTblorderdetailEntities(true, -1, -1);
    }

    public List<Tblorderdetail> findTblorderdetailEntities(int maxResults, int firstResult) {
        return findTblorderdetailEntities(false, maxResults, firstResult);
    }

    private List<Tblorderdetail> findTblorderdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblorderdetail.class));
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

    public Tblorderdetail findTblorderdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblorderdetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblorderdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblorderdetail> rt = cq.from(Tblorderdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
