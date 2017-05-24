/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.IllegalOrphanException;
import hd.JPA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbluser;
import hd.entity.Tblideabookphoto;
import hd.entity.Tblproject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblprojectJpaController implements Serializable {

    public TblprojectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblproject tblproject) {
        if (tblproject.getTblideabookphotoCollection() == null) {
            tblproject.setTblideabookphotoCollection(new ArrayList<Tblideabookphoto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbluser userID = tblproject.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                tblproject.setUserID(userID);
            }
            Collection<Tblideabookphoto> attachedTblideabookphotoCollection = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphotoToAttach : tblproject.getTblideabookphotoCollection()) {
                tblideabookphotoCollectionTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollection.add(tblideabookphotoCollectionTblideabookphotoToAttach);
            }
            tblproject.setTblideabookphotoCollection(attachedTblideabookphotoCollection);
            em.persist(tblproject);
            if (userID != null) {
                userID.getTblprojectCollection().add(tblproject);
                userID = em.merge(userID);
            }
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphoto : tblproject.getTblideabookphotoCollection()) {
                Tblproject oldTblProjectprojectIDOfTblideabookphotoCollectionTblideabookphoto = tblideabookphotoCollectionTblideabookphoto.getTblProjectprojectID();
                tblideabookphotoCollectionTblideabookphoto.setTblProjectprojectID(tblproject);
                tblideabookphotoCollectionTblideabookphoto = em.merge(tblideabookphotoCollectionTblideabookphoto);
                if (oldTblProjectprojectIDOfTblideabookphotoCollectionTblideabookphoto != null) {
                    oldTblProjectprojectIDOfTblideabookphotoCollectionTblideabookphoto.getTblideabookphotoCollection().remove(tblideabookphotoCollectionTblideabookphoto);
                    oldTblProjectprojectIDOfTblideabookphotoCollectionTblideabookphoto = em.merge(oldTblProjectprojectIDOfTblideabookphotoCollectionTblideabookphoto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblproject tblproject) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblproject persistentTblproject = em.find(Tblproject.class, tblproject.getProjectID());
            Tbluser userIDOld = persistentTblproject.getUserID();
            Tbluser userIDNew = tblproject.getUserID();
            Collection<Tblideabookphoto> tblideabookphotoCollectionOld = persistentTblproject.getTblideabookphotoCollection();
            Collection<Tblideabookphoto> tblideabookphotoCollectionNew = tblproject.getTblideabookphotoCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblideabookphoto tblideabookphotoCollectionOldTblideabookphoto : tblideabookphotoCollectionOld) {
                if (!tblideabookphotoCollectionNew.contains(tblideabookphotoCollectionOldTblideabookphoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblideabookphoto " + tblideabookphotoCollectionOldTblideabookphoto + " since its tblProjectprojectID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                tblproject.setUserID(userIDNew);
            }
            Collection<Tblideabookphoto> attachedTblideabookphotoCollectionNew = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphotoToAttach : tblideabookphotoCollectionNew) {
                tblideabookphotoCollectionNewTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionNewTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionNewTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollectionNew.add(tblideabookphotoCollectionNewTblideabookphotoToAttach);
            }
            tblideabookphotoCollectionNew = attachedTblideabookphotoCollectionNew;
            tblproject.setTblideabookphotoCollection(tblideabookphotoCollectionNew);
            tblproject = em.merge(tblproject);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getTblprojectCollection().remove(tblproject);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getTblprojectCollection().add(tblproject);
                userIDNew = em.merge(userIDNew);
            }
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphoto : tblideabookphotoCollectionNew) {
                if (!tblideabookphotoCollectionOld.contains(tblideabookphotoCollectionNewTblideabookphoto)) {
                    Tblproject oldTblProjectprojectIDOfTblideabookphotoCollectionNewTblideabookphoto = tblideabookphotoCollectionNewTblideabookphoto.getTblProjectprojectID();
                    tblideabookphotoCollectionNewTblideabookphoto.setTblProjectprojectID(tblproject);
                    tblideabookphotoCollectionNewTblideabookphoto = em.merge(tblideabookphotoCollectionNewTblideabookphoto);
                    if (oldTblProjectprojectIDOfTblideabookphotoCollectionNewTblideabookphoto != null && !oldTblProjectprojectIDOfTblideabookphotoCollectionNewTblideabookphoto.equals(tblproject)) {
                        oldTblProjectprojectIDOfTblideabookphotoCollectionNewTblideabookphoto.getTblideabookphotoCollection().remove(tblideabookphotoCollectionNewTblideabookphoto);
                        oldTblProjectprojectIDOfTblideabookphotoCollectionNewTblideabookphoto = em.merge(oldTblProjectprojectIDOfTblideabookphotoCollectionNewTblideabookphoto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblproject.getProjectID();
                if (findTblproject(id) == null) {
                    throw new NonexistentEntityException("The tblproject with id " + id + " no longer exists.");
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
            Tblproject tblproject;
            try {
                tblproject = em.getReference(Tblproject.class, id);
                tblproject.getProjectID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblproject with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblideabookphoto> tblideabookphotoCollectionOrphanCheck = tblproject.getTblideabookphotoCollection();
            for (Tblideabookphoto tblideabookphotoCollectionOrphanCheckTblideabookphoto : tblideabookphotoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblproject (" + tblproject + ") cannot be destroyed since the Tblideabookphoto " + tblideabookphotoCollectionOrphanCheckTblideabookphoto + " in its tblideabookphotoCollection field has a non-nullable tblProjectprojectID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbluser userID = tblproject.getUserID();
            if (userID != null) {
                userID.getTblprojectCollection().remove(tblproject);
                userID = em.merge(userID);
            }
            em.remove(tblproject);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblproject> findTblprojectEntities() {
        return findTblprojectEntities(true, -1, -1);
    }

    public List<Tblproject> findTblprojectEntities(int maxResults, int firstResult) {
        return findTblprojectEntities(false, maxResults, firstResult);
    }

    private List<Tblproject> findTblprojectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblproject.class));
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

    public Tblproject findTblproject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblproject.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblprojectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblproject> rt = cq.from(Tblproject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
