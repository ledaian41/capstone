/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tblideabook;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbluser;
import hd.entity.Tblideabookphoto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblideabookJpaController implements Serializable {

    public TblideabookJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblideabook tblideabook) {
        if (tblideabook.getTblideabookphotoCollection() == null) {
            tblideabook.setTblideabookphotoCollection(new ArrayList<Tblideabookphoto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbluser userID = tblideabook.getUserID();
            if (userID != null) {
                userID = em.getReference(userID.getClass(), userID.getUserID());
                tblideabook.setUserID(userID);
            }
            Collection<Tblideabookphoto> attachedTblideabookphotoCollection = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphotoToAttach : tblideabook.getTblideabookphotoCollection()) {
                tblideabookphotoCollectionTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollection.add(tblideabookphotoCollectionTblideabookphotoToAttach);
            }
            tblideabook.setTblideabookphotoCollection(attachedTblideabookphotoCollection);
            em.persist(tblideabook);
            if (userID != null) {
                userID.getTblideabookCollection().add(tblideabook);
                userID = em.merge(userID);
            }
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphoto : tblideabook.getTblideabookphotoCollection()) {
                tblideabookphotoCollectionTblideabookphoto.getTblideabookCollection().add(tblideabook);
                tblideabookphotoCollectionTblideabookphoto = em.merge(tblideabookphotoCollectionTblideabookphoto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblideabook tblideabook) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblideabook persistentTblideabook = em.find(Tblideabook.class, tblideabook.getIdeaBookID());
            Tbluser userIDOld = persistentTblideabook.getUserID();
            Tbluser userIDNew = tblideabook.getUserID();
            Collection<Tblideabookphoto> tblideabookphotoCollectionOld = persistentTblideabook.getTblideabookphotoCollection();
            Collection<Tblideabookphoto> tblideabookphotoCollectionNew = tblideabook.getTblideabookphotoCollection();
            if (userIDNew != null) {
                userIDNew = em.getReference(userIDNew.getClass(), userIDNew.getUserID());
                tblideabook.setUserID(userIDNew);
            }
            Collection<Tblideabookphoto> attachedTblideabookphotoCollectionNew = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphotoToAttach : tblideabookphotoCollectionNew) {
                tblideabookphotoCollectionNewTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionNewTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionNewTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollectionNew.add(tblideabookphotoCollectionNewTblideabookphotoToAttach);
            }
            tblideabookphotoCollectionNew = attachedTblideabookphotoCollectionNew;
            tblideabook.setTblideabookphotoCollection(tblideabookphotoCollectionNew);
            tblideabook = em.merge(tblideabook);
            if (userIDOld != null && !userIDOld.equals(userIDNew)) {
                userIDOld.getTblideabookCollection().remove(tblideabook);
                userIDOld = em.merge(userIDOld);
            }
            if (userIDNew != null && !userIDNew.equals(userIDOld)) {
                userIDNew.getTblideabookCollection().add(tblideabook);
                userIDNew = em.merge(userIDNew);
            }
            for (Tblideabookphoto tblideabookphotoCollectionOldTblideabookphoto : tblideabookphotoCollectionOld) {
                if (!tblideabookphotoCollectionNew.contains(tblideabookphotoCollectionOldTblideabookphoto)) {
                    tblideabookphotoCollectionOldTblideabookphoto.getTblideabookCollection().remove(tblideabook);
                    tblideabookphotoCollectionOldTblideabookphoto = em.merge(tblideabookphotoCollectionOldTblideabookphoto);
                }
            }
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphoto : tblideabookphotoCollectionNew) {
                if (!tblideabookphotoCollectionOld.contains(tblideabookphotoCollectionNewTblideabookphoto)) {
                    tblideabookphotoCollectionNewTblideabookphoto.getTblideabookCollection().add(tblideabook);
                    tblideabookphotoCollectionNewTblideabookphoto = em.merge(tblideabookphotoCollectionNewTblideabookphoto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblideabook.getIdeaBookID();
                if (findTblideabook(id) == null) {
                    throw new NonexistentEntityException("The tblideabook with id " + id + " no longer exists.");
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
            Tblideabook tblideabook;
            try {
                tblideabook = em.getReference(Tblideabook.class, id);
                tblideabook.getIdeaBookID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblideabook with id " + id + " no longer exists.", enfe);
            }
            Tbluser userID = tblideabook.getUserID();
            if (userID != null) {
                userID.getTblideabookCollection().remove(tblideabook);
                userID = em.merge(userID);
            }
            Collection<Tblideabookphoto> tblideabookphotoCollection = tblideabook.getTblideabookphotoCollection();
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphoto : tblideabookphotoCollection) {
                tblideabookphotoCollectionTblideabookphoto.getTblideabookCollection().remove(tblideabook);
                tblideabookphotoCollectionTblideabookphoto = em.merge(tblideabookphotoCollectionTblideabookphoto);
            }
            em.remove(tblideabook);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblideabook> findTblideabookEntities() {
        return findTblideabookEntities(true, -1, -1);
    }

    public List<Tblideabook> findTblideabookEntities(int maxResults, int firstResult) {
        return findTblideabookEntities(false, maxResults, firstResult);
    }

    private List<Tblideabook> findTblideabookEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblideabook.class));
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

    public Tblideabook findTblideabook(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblideabook.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblideabookCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblideabook> rt = cq.from(Tblideabook.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
