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
import hd.entity.Tblcategory;
import hd.entity.Tblproject;
import hd.entity.Tblstyle;
import hd.entity.Tblideabook;
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
public class TblideabookphotoJpaController implements Serializable {

    public TblideabookphotoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblideabookphoto tblideabookphoto) {
        if (tblideabookphoto.getTblideabookCollection() == null) {
            tblideabookphoto.setTblideabookCollection(new ArrayList<Tblideabook>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategory tblCategorycategoryID = tblideabookphoto.getTblCategorycategoryID();
            if (tblCategorycategoryID != null) {
                tblCategorycategoryID = em.getReference(tblCategorycategoryID.getClass(), tblCategorycategoryID.getCategoryID());
                tblideabookphoto.setTblCategorycategoryID(tblCategorycategoryID);
            }
            Tblproject tblProjectprojectID = tblideabookphoto.getTblProjectprojectID();
            if (tblProjectprojectID != null) {
                tblProjectprojectID = em.getReference(tblProjectprojectID.getClass(), tblProjectprojectID.getProjectID());
                tblideabookphoto.setTblProjectprojectID(tblProjectprojectID);
            }
            Tblstyle tblStylestyleID = tblideabookphoto.getTblStylestyleID();
            if (tblStylestyleID != null) {
                tblStylestyleID = em.getReference(tblStylestyleID.getClass(), tblStylestyleID.getStyleID());
                tblideabookphoto.setTblStylestyleID(tblStylestyleID);
            }
            Collection<Tblideabook> attachedTblideabookCollection = new ArrayList<Tblideabook>();
            for (Tblideabook tblideabookCollectionTblideabookToAttach : tblideabookphoto.getTblideabookCollection()) {
                tblideabookCollectionTblideabookToAttach = em.getReference(tblideabookCollectionTblideabookToAttach.getClass(), tblideabookCollectionTblideabookToAttach.getIdeaBookID());
                attachedTblideabookCollection.add(tblideabookCollectionTblideabookToAttach);
            }
            tblideabookphoto.setTblideabookCollection(attachedTblideabookCollection);
            em.persist(tblideabookphoto);
            if (tblCategorycategoryID != null) {
                tblCategorycategoryID.getTblideabookphotoCollection().add(tblideabookphoto);
                tblCategorycategoryID = em.merge(tblCategorycategoryID);
            }
            if (tblProjectprojectID != null) {
                tblProjectprojectID.getTblideabookphotoCollection().add(tblideabookphoto);
                tblProjectprojectID = em.merge(tblProjectprojectID);
            }
            if (tblStylestyleID != null) {
                tblStylestyleID.getTblideabookphotoCollection().add(tblideabookphoto);
                tblStylestyleID = em.merge(tblStylestyleID);
            }
            for (Tblideabook tblideabookCollectionTblideabook : tblideabookphoto.getTblideabookCollection()) {
                tblideabookCollectionTblideabook.getTblideabookphotoCollection().add(tblideabookphoto);
                tblideabookCollectionTblideabook = em.merge(tblideabookCollectionTblideabook);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblideabookphoto tblideabookphoto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblideabookphoto persistentTblideabookphoto = em.find(Tblideabookphoto.class, tblideabookphoto.getPhotoID());
            Tblcategory tblCategorycategoryIDOld = persistentTblideabookphoto.getTblCategorycategoryID();
            Tblcategory tblCategorycategoryIDNew = tblideabookphoto.getTblCategorycategoryID();
            Tblproject tblProjectprojectIDOld = persistentTblideabookphoto.getTblProjectprojectID();
            Tblproject tblProjectprojectIDNew = tblideabookphoto.getTblProjectprojectID();
            Tblstyle tblStylestyleIDOld = persistentTblideabookphoto.getTblStylestyleID();
            Tblstyle tblStylestyleIDNew = tblideabookphoto.getTblStylestyleID();
            Collection<Tblideabook> tblideabookCollectionOld = persistentTblideabookphoto.getTblideabookCollection();
            Collection<Tblideabook> tblideabookCollectionNew = tblideabookphoto.getTblideabookCollection();
            if (tblCategorycategoryIDNew != null) {
                tblCategorycategoryIDNew = em.getReference(tblCategorycategoryIDNew.getClass(), tblCategorycategoryIDNew.getCategoryID());
                tblideabookphoto.setTblCategorycategoryID(tblCategorycategoryIDNew);
            }
            if (tblProjectprojectIDNew != null) {
                tblProjectprojectIDNew = em.getReference(tblProjectprojectIDNew.getClass(), tblProjectprojectIDNew.getProjectID());
                tblideabookphoto.setTblProjectprojectID(tblProjectprojectIDNew);
            }
            if (tblStylestyleIDNew != null) {
                tblStylestyleIDNew = em.getReference(tblStylestyleIDNew.getClass(), tblStylestyleIDNew.getStyleID());
                tblideabookphoto.setTblStylestyleID(tblStylestyleIDNew);
            }
            Collection<Tblideabook> attachedTblideabookCollectionNew = new ArrayList<Tblideabook>();
            for (Tblideabook tblideabookCollectionNewTblideabookToAttach : tblideabookCollectionNew) {
                tblideabookCollectionNewTblideabookToAttach = em.getReference(tblideabookCollectionNewTblideabookToAttach.getClass(), tblideabookCollectionNewTblideabookToAttach.getIdeaBookID());
                attachedTblideabookCollectionNew.add(tblideabookCollectionNewTblideabookToAttach);
            }
            tblideabookCollectionNew = attachedTblideabookCollectionNew;
            tblideabookphoto.setTblideabookCollection(tblideabookCollectionNew);
            tblideabookphoto = em.merge(tblideabookphoto);
            if (tblCategorycategoryIDOld != null && !tblCategorycategoryIDOld.equals(tblCategorycategoryIDNew)) {
                tblCategorycategoryIDOld.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblCategorycategoryIDOld = em.merge(tblCategorycategoryIDOld);
            }
            if (tblCategorycategoryIDNew != null && !tblCategorycategoryIDNew.equals(tblCategorycategoryIDOld)) {
                tblCategorycategoryIDNew.getTblideabookphotoCollection().add(tblideabookphoto);
                tblCategorycategoryIDNew = em.merge(tblCategorycategoryIDNew);
            }
            if (tblProjectprojectIDOld != null && !tblProjectprojectIDOld.equals(tblProjectprojectIDNew)) {
                tblProjectprojectIDOld.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblProjectprojectIDOld = em.merge(tblProjectprojectIDOld);
            }
            if (tblProjectprojectIDNew != null && !tblProjectprojectIDNew.equals(tblProjectprojectIDOld)) {
                tblProjectprojectIDNew.getTblideabookphotoCollection().add(tblideabookphoto);
                tblProjectprojectIDNew = em.merge(tblProjectprojectIDNew);
            }
            if (tblStylestyleIDOld != null && !tblStylestyleIDOld.equals(tblStylestyleIDNew)) {
                tblStylestyleIDOld.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblStylestyleIDOld = em.merge(tblStylestyleIDOld);
            }
            if (tblStylestyleIDNew != null && !tblStylestyleIDNew.equals(tblStylestyleIDOld)) {
                tblStylestyleIDNew.getTblideabookphotoCollection().add(tblideabookphoto);
                tblStylestyleIDNew = em.merge(tblStylestyleIDNew);
            }
            for (Tblideabook tblideabookCollectionOldTblideabook : tblideabookCollectionOld) {
                if (!tblideabookCollectionNew.contains(tblideabookCollectionOldTblideabook)) {
                    tblideabookCollectionOldTblideabook.getTblideabookphotoCollection().remove(tblideabookphoto);
                    tblideabookCollectionOldTblideabook = em.merge(tblideabookCollectionOldTblideabook);
                }
            }
            for (Tblideabook tblideabookCollectionNewTblideabook : tblideabookCollectionNew) {
                if (!tblideabookCollectionOld.contains(tblideabookCollectionNewTblideabook)) {
                    tblideabookCollectionNewTblideabook.getTblideabookphotoCollection().add(tblideabookphoto);
                    tblideabookCollectionNewTblideabook = em.merge(tblideabookCollectionNewTblideabook);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblideabookphoto.getPhotoID();
                if (findTblideabookphoto(id) == null) {
                    throw new NonexistentEntityException("The tblideabookphoto with id " + id + " no longer exists.");
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
            Tblideabookphoto tblideabookphoto;
            try {
                tblideabookphoto = em.getReference(Tblideabookphoto.class, id);
                tblideabookphoto.getPhotoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblideabookphoto with id " + id + " no longer exists.", enfe);
            }
            Tblcategory tblCategorycategoryID = tblideabookphoto.getTblCategorycategoryID();
            if (tblCategorycategoryID != null) {
                tblCategorycategoryID.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblCategorycategoryID = em.merge(tblCategorycategoryID);
            }
            Tblproject tblProjectprojectID = tblideabookphoto.getTblProjectprojectID();
            if (tblProjectprojectID != null) {
                tblProjectprojectID.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblProjectprojectID = em.merge(tblProjectprojectID);
            }
            Tblstyle tblStylestyleID = tblideabookphoto.getTblStylestyleID();
            if (tblStylestyleID != null) {
                tblStylestyleID.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblStylestyleID = em.merge(tblStylestyleID);
            }
            Collection<Tblideabook> tblideabookCollection = tblideabookphoto.getTblideabookCollection();
            for (Tblideabook tblideabookCollectionTblideabook : tblideabookCollection) {
                tblideabookCollectionTblideabook.getTblideabookphotoCollection().remove(tblideabookphoto);
                tblideabookCollectionTblideabook = em.merge(tblideabookCollectionTblideabook);
            }
            em.remove(tblideabookphoto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblideabookphoto> findTblideabookphotoEntities() {
        return findTblideabookphotoEntities(true, -1, -1);
    }

    public List<Tblideabookphoto> findTblideabookphotoEntities(int maxResults, int firstResult) {
        return findTblideabookphotoEntities(false, maxResults, firstResult);
    }

    private List<Tblideabookphoto> findTblideabookphotoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblideabookphoto.class));
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

    public Tblideabookphoto findTblideabookphoto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblideabookphoto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblideabookphotoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblideabookphoto> rt = cq.from(Tblideabookphoto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
