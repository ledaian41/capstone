/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.IllegalOrphanException;
import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tblorder;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbluser;
import hd.entity.Tblorderdetail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblorderJpaController implements Serializable {

    public TblorderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblorder tblorder) {
        if (tblorder.getTblorderdetailCollection() == null) {
            tblorder.setTblorderdetailCollection(new ArrayList<Tblorderdetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbluser userID = tblorder.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                tblorder.setUserID(userID);
            }
            Collection<Tblorderdetail> attachedTblorderdetailCollection = new ArrayList<Tblorderdetail>();
            for (Tblorderdetail tblorderdetailCollectionTblorderdetailToAttach : tblorder.getTblorderdetailCollection()) {
                tblorderdetailCollectionTblorderdetailToAttach = em.getReference(tblorderdetailCollectionTblorderdetailToAttach.getClass(), tblorderdetailCollectionTblorderdetailToAttach.getOrderDetailID());
                attachedTblorderdetailCollection.add(tblorderdetailCollectionTblorderdetailToAttach);
            }
            tblorder.setTblorderdetailCollection(attachedTblorderdetailCollection);
            em.persist(tblorder);
            if (userID != null) {
                userID.getTblorderCollection().add(tblorder);
                userID = em.merge(userID);
            }
            for (Tblorderdetail tblorderdetailCollectionTblorderdetail : tblorder.getTblorderdetailCollection()) {
                Tblorder oldOrderIDOfTblorderdetailCollectionTblorderdetail = tblorderdetailCollectionTblorderdetail.getOrderID();
                tblorderdetailCollectionTblorderdetail.setOrderID(tblorder);
                tblorderdetailCollectionTblorderdetail = em.merge(tblorderdetailCollectionTblorderdetail);
                if (oldOrderIDOfTblorderdetailCollectionTblorderdetail != null) {
                    oldOrderIDOfTblorderdetailCollectionTblorderdetail.getTblorderdetailCollection().remove(tblorderdetailCollectionTblorderdetail);
                    oldOrderIDOfTblorderdetailCollectionTblorderdetail = em.merge(oldOrderIDOfTblorderdetailCollectionTblorderdetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblorder tblorder) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblorder persistentTblorder = em.find(Tblorder.class, tblorder.getOrderID());
            Tbluser userIDOld = persistentTblorder.getUserID();
            Tbluser userIDNew = tblorder.getUserID();
            Collection<Tblorderdetail> tblorderdetailCollectionOld = persistentTblorder.getTblorderdetailCollection();
            Collection<Tblorderdetail> tblorderdetailCollectionNew = tblorder.getTblorderdetailCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblorderdetail tblorderdetailCollectionOldTblorderdetail : tblorderdetailCollectionOld) {
                if (!tblorderdetailCollectionNew.contains(tblorderdetailCollectionOldTblorderdetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblorderdetail " + tblorderdetailCollectionOldTblorderdetail + " since its orderID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                tblorder.setUserID(userIDNew);
            }
            Collection<Tblorderdetail> attachedTblorderdetailCollectionNew = new ArrayList<Tblorderdetail>();
            for (Tblorderdetail tblorderdetailCollectionNewTblorderdetailToAttach : tblorderdetailCollectionNew) {
                tblorderdetailCollectionNewTblorderdetailToAttach = em.getReference(tblorderdetailCollectionNewTblorderdetailToAttach.getClass(), tblorderdetailCollectionNewTblorderdetailToAttach.getOrderDetailID());
                attachedTblorderdetailCollectionNew.add(tblorderdetailCollectionNewTblorderdetailToAttach);
            }
            tblorderdetailCollectionNew = attachedTblorderdetailCollectionNew;
            tblorder.setTblorderdetailCollection(tblorderdetailCollectionNew);
            tblorder = em.merge(tblorder);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getTblorderCollection().remove(tblorder);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getTblorderCollection().add(tblorder);
                userIDNew = em.merge(userIDNew);
            }
            for (Tblorderdetail tblorderdetailCollectionNewTblorderdetail : tblorderdetailCollectionNew) {
                if (!tblorderdetailCollectionOld.contains(tblorderdetailCollectionNewTblorderdetail)) {
                    Tblorder oldOrderIDOfTblorderdetailCollectionNewTblorderdetail = tblorderdetailCollectionNewTblorderdetail.getOrderID();
                    tblorderdetailCollectionNewTblorderdetail.setOrderID(tblorder);
                    tblorderdetailCollectionNewTblorderdetail = em.merge(tblorderdetailCollectionNewTblorderdetail);
                    if (oldOrderIDOfTblorderdetailCollectionNewTblorderdetail != null && !oldOrderIDOfTblorderdetailCollectionNewTblorderdetail.equals(tblorder)) {
                        oldOrderIDOfTblorderdetailCollectionNewTblorderdetail.getTblorderdetailCollection().remove(tblorderdetailCollectionNewTblorderdetail);
                        oldOrderIDOfTblorderdetailCollectionNewTblorderdetail = em.merge(oldOrderIDOfTblorderdetailCollectionNewTblorderdetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblorder.getOrderID();
                if (findTblorder(id) == null) {
                    throw new NonexistentEntityException("The tblorder with id " + id + " no longer exists.");
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
            Tblorder tblorder;
            try {
                tblorder = em.getReference(Tblorder.class, id);
                tblorder.getOrderID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblorder with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblorderdetail> tblorderdetailCollectionOrphanCheck = tblorder.getTblorderdetailCollection();
            for (Tblorderdetail tblorderdetailCollectionOrphanCheckTblorderdetail : tblorderdetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblorder (" + tblorder + ") cannot be destroyed since the Tblorderdetail " + tblorderdetailCollectionOrphanCheckTblorderdetail + " in its tblorderdetailCollection field has a non-nullable orderID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbluser userID = tblorder.getUserID();
            if (userID != null) {
                userID.getTblorderCollection().remove(tblorder);
                userID = em.merge(userID);
            }
            em.remove(tblorder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblorder> findTblorderEntities() {
        return findTblorderEntities(true, -1, -1);
    }

    public List<Tblorder> findTblorderEntities(int maxResults, int firstResult) {
        return findTblorderEntities(false, maxResults, firstResult);
    }

    private List<Tblorder> findTblorderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblorder.class));
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

    public Tblorder findTblorder(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblorder.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblorderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblorder> rt = cq.from(Tblorder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
