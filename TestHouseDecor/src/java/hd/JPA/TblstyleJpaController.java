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
import hd.entity.Tblideabookphoto;
import java.util.ArrayList;
import java.util.Collection;
import hd.entity.Tblproductphoto;
import hd.entity.Tblstyle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblstyleJpaController implements Serializable {

    public TblstyleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblstyle tblstyle) {
        if (tblstyle.getTblideabookphotoCollection() == null) {
            tblstyle.setTblideabookphotoCollection(new ArrayList<Tblideabookphoto>());
        }
        if (tblstyle.getTblproductphotoCollection() == null) {
            tblstyle.setTblproductphotoCollection(new ArrayList<Tblproductphoto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblideabookphoto> attachedTblideabookphotoCollection = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphotoToAttach : tblstyle.getTblideabookphotoCollection()) {
                tblideabookphotoCollectionTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollection.add(tblideabookphotoCollectionTblideabookphotoToAttach);
            }
            tblstyle.setTblideabookphotoCollection(attachedTblideabookphotoCollection);
            Collection<Tblproductphoto> attachedTblproductphotoCollection = new ArrayList<Tblproductphoto>();
            for (Tblproductphoto tblproductphotoCollectionTblproductphotoToAttach : tblstyle.getTblproductphotoCollection()) {
                tblproductphotoCollectionTblproductphotoToAttach = em.getReference(tblproductphotoCollectionTblproductphotoToAttach.getClass(), tblproductphotoCollectionTblproductphotoToAttach.getProductPhotoID());
                attachedTblproductphotoCollection.add(tblproductphotoCollectionTblproductphotoToAttach);
            }
            tblstyle.setTblproductphotoCollection(attachedTblproductphotoCollection);
            em.persist(tblstyle);
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphoto : tblstyle.getTblideabookphotoCollection()) {
                Tblstyle oldTblStylestyleIDOfTblideabookphotoCollectionTblideabookphoto = tblideabookphotoCollectionTblideabookphoto.getTblStylestyleID();
                tblideabookphotoCollectionTblideabookphoto.setTblStylestyleID(tblstyle);
                tblideabookphotoCollectionTblideabookphoto = em.merge(tblideabookphotoCollectionTblideabookphoto);
                if (oldTblStylestyleIDOfTblideabookphotoCollectionTblideabookphoto != null) {
                    oldTblStylestyleIDOfTblideabookphotoCollectionTblideabookphoto.getTblideabookphotoCollection().remove(tblideabookphotoCollectionTblideabookphoto);
                    oldTblStylestyleIDOfTblideabookphotoCollectionTblideabookphoto = em.merge(oldTblStylestyleIDOfTblideabookphotoCollectionTblideabookphoto);
                }
            }
            for (Tblproductphoto tblproductphotoCollectionTblproductphoto : tblstyle.getTblproductphotoCollection()) {
                Tblstyle oldTblStylestyleIDOfTblproductphotoCollectionTblproductphoto = tblproductphotoCollectionTblproductphoto.getTblStylestyleID();
                tblproductphotoCollectionTblproductphoto.setTblStylestyleID(tblstyle);
                tblproductphotoCollectionTblproductphoto = em.merge(tblproductphotoCollectionTblproductphoto);
                if (oldTblStylestyleIDOfTblproductphotoCollectionTblproductphoto != null) {
                    oldTblStylestyleIDOfTblproductphotoCollectionTblproductphoto.getTblproductphotoCollection().remove(tblproductphotoCollectionTblproductphoto);
                    oldTblStylestyleIDOfTblproductphotoCollectionTblproductphoto = em.merge(oldTblStylestyleIDOfTblproductphotoCollectionTblproductphoto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblstyle tblstyle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblstyle persistentTblstyle = em.find(Tblstyle.class, tblstyle.getStyleID());
            Collection<Tblideabookphoto> tblideabookphotoCollectionOld = persistentTblstyle.getTblideabookphotoCollection();
            Collection<Tblideabookphoto> tblideabookphotoCollectionNew = tblstyle.getTblideabookphotoCollection();
            Collection<Tblproductphoto> tblproductphotoCollectionOld = persistentTblstyle.getTblproductphotoCollection();
            Collection<Tblproductphoto> tblproductphotoCollectionNew = tblstyle.getTblproductphotoCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblideabookphoto tblideabookphotoCollectionOldTblideabookphoto : tblideabookphotoCollectionOld) {
                if (!tblideabookphotoCollectionNew.contains(tblideabookphotoCollectionOldTblideabookphoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblideabookphoto " + tblideabookphotoCollectionOldTblideabookphoto + " since its tblStylestyleID field is not nullable.");
                }
            }
            for (Tblproductphoto tblproductphotoCollectionOldTblproductphoto : tblproductphotoCollectionOld) {
                if (!tblproductphotoCollectionNew.contains(tblproductphotoCollectionOldTblproductphoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblproductphoto " + tblproductphotoCollectionOldTblproductphoto + " since its tblStylestyleID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblideabookphoto> attachedTblideabookphotoCollectionNew = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphotoToAttach : tblideabookphotoCollectionNew) {
                tblideabookphotoCollectionNewTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionNewTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionNewTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollectionNew.add(tblideabookphotoCollectionNewTblideabookphotoToAttach);
            }
            tblideabookphotoCollectionNew = attachedTblideabookphotoCollectionNew;
            tblstyle.setTblideabookphotoCollection(tblideabookphotoCollectionNew);
            Collection<Tblproductphoto> attachedTblproductphotoCollectionNew = new ArrayList<Tblproductphoto>();
            for (Tblproductphoto tblproductphotoCollectionNewTblproductphotoToAttach : tblproductphotoCollectionNew) {
                tblproductphotoCollectionNewTblproductphotoToAttach = em.getReference(tblproductphotoCollectionNewTblproductphotoToAttach.getClass(), tblproductphotoCollectionNewTblproductphotoToAttach.getProductPhotoID());
                attachedTblproductphotoCollectionNew.add(tblproductphotoCollectionNewTblproductphotoToAttach);
            }
            tblproductphotoCollectionNew = attachedTblproductphotoCollectionNew;
            tblstyle.setTblproductphotoCollection(tblproductphotoCollectionNew);
            tblstyle = em.merge(tblstyle);
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphoto : tblideabookphotoCollectionNew) {
                if (!tblideabookphotoCollectionOld.contains(tblideabookphotoCollectionNewTblideabookphoto)) {
                    Tblstyle oldTblStylestyleIDOfTblideabookphotoCollectionNewTblideabookphoto = tblideabookphotoCollectionNewTblideabookphoto.getTblStylestyleID();
                    tblideabookphotoCollectionNewTblideabookphoto.setTblStylestyleID(tblstyle);
                    tblideabookphotoCollectionNewTblideabookphoto = em.merge(tblideabookphotoCollectionNewTblideabookphoto);
                    if (oldTblStylestyleIDOfTblideabookphotoCollectionNewTblideabookphoto != null && !oldTblStylestyleIDOfTblideabookphotoCollectionNewTblideabookphoto.equals(tblstyle)) {
                        oldTblStylestyleIDOfTblideabookphotoCollectionNewTblideabookphoto.getTblideabookphotoCollection().remove(tblideabookphotoCollectionNewTblideabookphoto);
                        oldTblStylestyleIDOfTblideabookphotoCollectionNewTblideabookphoto = em.merge(oldTblStylestyleIDOfTblideabookphotoCollectionNewTblideabookphoto);
                    }
                }
            }
            for (Tblproductphoto tblproductphotoCollectionNewTblproductphoto : tblproductphotoCollectionNew) {
                if (!tblproductphotoCollectionOld.contains(tblproductphotoCollectionNewTblproductphoto)) {
                    Tblstyle oldTblStylestyleIDOfTblproductphotoCollectionNewTblproductphoto = tblproductphotoCollectionNewTblproductphoto.getTblStylestyleID();
                    tblproductphotoCollectionNewTblproductphoto.setTblStylestyleID(tblstyle);
                    tblproductphotoCollectionNewTblproductphoto = em.merge(tblproductphotoCollectionNewTblproductphoto);
                    if (oldTblStylestyleIDOfTblproductphotoCollectionNewTblproductphoto != null && !oldTblStylestyleIDOfTblproductphotoCollectionNewTblproductphoto.equals(tblstyle)) {
                        oldTblStylestyleIDOfTblproductphotoCollectionNewTblproductphoto.getTblproductphotoCollection().remove(tblproductphotoCollectionNewTblproductphoto);
                        oldTblStylestyleIDOfTblproductphotoCollectionNewTblproductphoto = em.merge(oldTblStylestyleIDOfTblproductphotoCollectionNewTblproductphoto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblstyle.getStyleID();
                if (findTblstyle(id) == null) {
                    throw new NonexistentEntityException("The tblstyle with id " + id + " no longer exists.");
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
            Tblstyle tblstyle;
            try {
                tblstyle = em.getReference(Tblstyle.class, id);
                tblstyle.getStyleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblstyle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblideabookphoto> tblideabookphotoCollectionOrphanCheck = tblstyle.getTblideabookphotoCollection();
            for (Tblideabookphoto tblideabookphotoCollectionOrphanCheckTblideabookphoto : tblideabookphotoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblstyle (" + tblstyle + ") cannot be destroyed since the Tblideabookphoto " + tblideabookphotoCollectionOrphanCheckTblideabookphoto + " in its tblideabookphotoCollection field has a non-nullable tblStylestyleID field.");
            }
            Collection<Tblproductphoto> tblproductphotoCollectionOrphanCheck = tblstyle.getTblproductphotoCollection();
            for (Tblproductphoto tblproductphotoCollectionOrphanCheckTblproductphoto : tblproductphotoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblstyle (" + tblstyle + ") cannot be destroyed since the Tblproductphoto " + tblproductphotoCollectionOrphanCheckTblproductphoto + " in its tblproductphotoCollection field has a non-nullable tblStylestyleID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tblstyle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblstyle> findTblstyleEntities() {
        return findTblstyleEntities(true, -1, -1);
    }

    public List<Tblstyle> findTblstyleEntities(int maxResults, int firstResult) {
        return findTblstyleEntities(false, maxResults, firstResult);
    }

    private List<Tblstyle> findTblstyleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblstyle.class));
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

    public Tblstyle findTblstyle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblstyle.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblstyleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblstyle> rt = cq.from(Tblstyle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
