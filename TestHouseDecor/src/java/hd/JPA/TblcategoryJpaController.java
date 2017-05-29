/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.IllegalOrphanException;
import hd.JPA.exceptions.NonexistentEntityException;
import hd.entity.Tblcategory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbltracking;
import java.util.ArrayList;
import java.util.Collection;
import hd.entity.Tblideabookphoto;
import hd.entity.Tblproductphoto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblcategoryJpaController implements Serializable {

    public TblcategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblcategory tblcategory) {
        if (tblcategory.getTbltrackingCollection() == null) {
            tblcategory.setTbltrackingCollection(new ArrayList<Tbltracking>());
        }
        if (tblcategory.getTblideabookphotoCollection() == null) {
            tblcategory.setTblideabookphotoCollection(new ArrayList<Tblideabookphoto>());
        }
        if (tblcategory.getTblproductphotoCollection() == null) {
            tblcategory.setTblproductphotoCollection(new ArrayList<Tblproductphoto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbltracking> attachedTbltrackingCollection = new ArrayList<Tbltracking>();
            for (Tbltracking tbltrackingCollectionTbltrackingToAttach : tblcategory.getTbltrackingCollection()) {
                tbltrackingCollectionTbltrackingToAttach = em.getReference(tbltrackingCollectionTbltrackingToAttach.getClass(), tbltrackingCollectionTbltrackingToAttach.getTbltrackingPK());
                attachedTbltrackingCollection.add(tbltrackingCollectionTbltrackingToAttach);
            }
            tblcategory.setTbltrackingCollection(attachedTbltrackingCollection);
            Collection<Tblideabookphoto> attachedTblideabookphotoCollection = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphotoToAttach : tblcategory.getTblideabookphotoCollection()) {
                tblideabookphotoCollectionTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollection.add(tblideabookphotoCollectionTblideabookphotoToAttach);
            }
            tblcategory.setTblideabookphotoCollection(attachedTblideabookphotoCollection);
            Collection<Tblproductphoto> attachedTblproductphotoCollection = new ArrayList<Tblproductphoto>();
            for (Tblproductphoto tblproductphotoCollectionTblproductphotoToAttach : tblcategory.getTblproductphotoCollection()) {
                tblproductphotoCollectionTblproductphotoToAttach = em.getReference(tblproductphotoCollectionTblproductphotoToAttach.getClass(), tblproductphotoCollectionTblproductphotoToAttach.getProductPhotoID());
                attachedTblproductphotoCollection.add(tblproductphotoCollectionTblproductphotoToAttach);
            }
            tblcategory.setTblproductphotoCollection(attachedTblproductphotoCollection);
            em.persist(tblcategory);
            for (Tbltracking tbltrackingCollectionTbltracking : tblcategory.getTbltrackingCollection()) {
                Tblcategory oldTblcategoryOfTbltrackingCollectionTbltracking = tbltrackingCollectionTbltracking.getTblcategory();
                tbltrackingCollectionTbltracking.setTblcategory(tblcategory);
                tbltrackingCollectionTbltracking = em.merge(tbltrackingCollectionTbltracking);
                if (oldTblcategoryOfTbltrackingCollectionTbltracking != null) {
                    oldTblcategoryOfTbltrackingCollectionTbltracking.getTbltrackingCollection().remove(tbltrackingCollectionTbltracking);
                    oldTblcategoryOfTbltrackingCollectionTbltracking = em.merge(oldTblcategoryOfTbltrackingCollectionTbltracking);
                }
            }
            for (Tblideabookphoto tblideabookphotoCollectionTblideabookphoto : tblcategory.getTblideabookphotoCollection()) {
                Tblcategory oldTblCategorycategoryIDOfTblideabookphotoCollectionTblideabookphoto = tblideabookphotoCollectionTblideabookphoto.getTblCategorycategoryID();
                tblideabookphotoCollectionTblideabookphoto.setTblCategorycategoryID(tblcategory);
                tblideabookphotoCollectionTblideabookphoto = em.merge(tblideabookphotoCollectionTblideabookphoto);
                if (oldTblCategorycategoryIDOfTblideabookphotoCollectionTblideabookphoto != null) {
                    oldTblCategorycategoryIDOfTblideabookphotoCollectionTblideabookphoto.getTblideabookphotoCollection().remove(tblideabookphotoCollectionTblideabookphoto);
                    oldTblCategorycategoryIDOfTblideabookphotoCollectionTblideabookphoto = em.merge(oldTblCategorycategoryIDOfTblideabookphotoCollectionTblideabookphoto);
                }
            }
            for (Tblproductphoto tblproductphotoCollectionTblproductphoto : tblcategory.getTblproductphotoCollection()) {
                Tblcategory oldTblCategorycategoryIDOfTblproductphotoCollectionTblproductphoto = tblproductphotoCollectionTblproductphoto.getTblCategorycategoryID();
                tblproductphotoCollectionTblproductphoto.setTblCategorycategoryID(tblcategory);
                tblproductphotoCollectionTblproductphoto = em.merge(tblproductphotoCollectionTblproductphoto);
                if (oldTblCategorycategoryIDOfTblproductphotoCollectionTblproductphoto != null) {
                    oldTblCategorycategoryIDOfTblproductphotoCollectionTblproductphoto.getTblproductphotoCollection().remove(tblproductphotoCollectionTblproductphoto);
                    oldTblCategorycategoryIDOfTblproductphotoCollectionTblproductphoto = em.merge(oldTblCategorycategoryIDOfTblproductphotoCollectionTblproductphoto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblcategory tblcategory) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategory persistentTblcategory = em.find(Tblcategory.class, tblcategory.getCategoryID());
            Collection<Tbltracking> tbltrackingCollectionOld = persistentTblcategory.getTbltrackingCollection();
            Collection<Tbltracking> tbltrackingCollectionNew = tblcategory.getTbltrackingCollection();
            Collection<Tblideabookphoto> tblideabookphotoCollectionOld = persistentTblcategory.getTblideabookphotoCollection();
            Collection<Tblideabookphoto> tblideabookphotoCollectionNew = tblcategory.getTblideabookphotoCollection();
            Collection<Tblproductphoto> tblproductphotoCollectionOld = persistentTblcategory.getTblproductphotoCollection();
            Collection<Tblproductphoto> tblproductphotoCollectionNew = tblcategory.getTblproductphotoCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbltracking tbltrackingCollectionOldTbltracking : tbltrackingCollectionOld) {
                if (!tbltrackingCollectionNew.contains(tbltrackingCollectionOldTbltracking)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbltracking " + tbltrackingCollectionOldTbltracking + " since its tblcategory field is not nullable.");
                }
            }
            for (Tblideabookphoto tblideabookphotoCollectionOldTblideabookphoto : tblideabookphotoCollectionOld) {
                if (!tblideabookphotoCollectionNew.contains(tblideabookphotoCollectionOldTblideabookphoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblideabookphoto " + tblideabookphotoCollectionOldTblideabookphoto + " since its tblCategorycategoryID field is not nullable.");
                }
            }
            for (Tblproductphoto tblproductphotoCollectionOldTblproductphoto : tblproductphotoCollectionOld) {
                if (!tblproductphotoCollectionNew.contains(tblproductphotoCollectionOldTblproductphoto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblproductphoto " + tblproductphotoCollectionOldTblproductphoto + " since its tblCategorycategoryID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tbltracking> attachedTbltrackingCollectionNew = new ArrayList<Tbltracking>();
            for (Tbltracking tbltrackingCollectionNewTbltrackingToAttach : tbltrackingCollectionNew) {
                tbltrackingCollectionNewTbltrackingToAttach = em.getReference(tbltrackingCollectionNewTbltrackingToAttach.getClass(), tbltrackingCollectionNewTbltrackingToAttach.getTbltrackingPK());
                attachedTbltrackingCollectionNew.add(tbltrackingCollectionNewTbltrackingToAttach);
            }
            tbltrackingCollectionNew = attachedTbltrackingCollectionNew;
            tblcategory.setTbltrackingCollection(tbltrackingCollectionNew);
            Collection<Tblideabookphoto> attachedTblideabookphotoCollectionNew = new ArrayList<Tblideabookphoto>();
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphotoToAttach : tblideabookphotoCollectionNew) {
                tblideabookphotoCollectionNewTblideabookphotoToAttach = em.getReference(tblideabookphotoCollectionNewTblideabookphotoToAttach.getClass(), tblideabookphotoCollectionNewTblideabookphotoToAttach.getPhotoID());
                attachedTblideabookphotoCollectionNew.add(tblideabookphotoCollectionNewTblideabookphotoToAttach);
            }
            tblideabookphotoCollectionNew = attachedTblideabookphotoCollectionNew;
            tblcategory.setTblideabookphotoCollection(tblideabookphotoCollectionNew);
            Collection<Tblproductphoto> attachedTblproductphotoCollectionNew = new ArrayList<Tblproductphoto>();
            for (Tblproductphoto tblproductphotoCollectionNewTblproductphotoToAttach : tblproductphotoCollectionNew) {
                tblproductphotoCollectionNewTblproductphotoToAttach = em.getReference(tblproductphotoCollectionNewTblproductphotoToAttach.getClass(), tblproductphotoCollectionNewTblproductphotoToAttach.getProductPhotoID());
                attachedTblproductphotoCollectionNew.add(tblproductphotoCollectionNewTblproductphotoToAttach);
            }
            tblproductphotoCollectionNew = attachedTblproductphotoCollectionNew;
            tblcategory.setTblproductphotoCollection(tblproductphotoCollectionNew);
            tblcategory = em.merge(tblcategory);
            for (Tbltracking tbltrackingCollectionNewTbltracking : tbltrackingCollectionNew) {
                if (!tbltrackingCollectionOld.contains(tbltrackingCollectionNewTbltracking)) {
                    Tblcategory oldTblcategoryOfTbltrackingCollectionNewTbltracking = tbltrackingCollectionNewTbltracking.getTblcategory();
                    tbltrackingCollectionNewTbltracking.setTblcategory(tblcategory);
                    tbltrackingCollectionNewTbltracking = em.merge(tbltrackingCollectionNewTbltracking);
                    if (oldTblcategoryOfTbltrackingCollectionNewTbltracking != null && !oldTblcategoryOfTbltrackingCollectionNewTbltracking.equals(tblcategory)) {
                        oldTblcategoryOfTbltrackingCollectionNewTbltracking.getTbltrackingCollection().remove(tbltrackingCollectionNewTbltracking);
                        oldTblcategoryOfTbltrackingCollectionNewTbltracking = em.merge(oldTblcategoryOfTbltrackingCollectionNewTbltracking);
                    }
                }
            }
            for (Tblideabookphoto tblideabookphotoCollectionNewTblideabookphoto : tblideabookphotoCollectionNew) {
                if (!tblideabookphotoCollectionOld.contains(tblideabookphotoCollectionNewTblideabookphoto)) {
                    Tblcategory oldTblCategorycategoryIDOfTblideabookphotoCollectionNewTblideabookphoto = tblideabookphotoCollectionNewTblideabookphoto.getTblCategorycategoryID();
                    tblideabookphotoCollectionNewTblideabookphoto.setTblCategorycategoryID(tblcategory);
                    tblideabookphotoCollectionNewTblideabookphoto = em.merge(tblideabookphotoCollectionNewTblideabookphoto);
                    if (oldTblCategorycategoryIDOfTblideabookphotoCollectionNewTblideabookphoto != null && !oldTblCategorycategoryIDOfTblideabookphotoCollectionNewTblideabookphoto.equals(tblcategory)) {
                        oldTblCategorycategoryIDOfTblideabookphotoCollectionNewTblideabookphoto.getTblideabookphotoCollection().remove(tblideabookphotoCollectionNewTblideabookphoto);
                        oldTblCategorycategoryIDOfTblideabookphotoCollectionNewTblideabookphoto = em.merge(oldTblCategorycategoryIDOfTblideabookphotoCollectionNewTblideabookphoto);
                    }
                }
            }
            for (Tblproductphoto tblproductphotoCollectionNewTblproductphoto : tblproductphotoCollectionNew) {
                if (!tblproductphotoCollectionOld.contains(tblproductphotoCollectionNewTblproductphoto)) {
                    Tblcategory oldTblCategorycategoryIDOfTblproductphotoCollectionNewTblproductphoto = tblproductphotoCollectionNewTblproductphoto.getTblCategorycategoryID();
                    tblproductphotoCollectionNewTblproductphoto.setTblCategorycategoryID(tblcategory);
                    tblproductphotoCollectionNewTblproductphoto = em.merge(tblproductphotoCollectionNewTblproductphoto);
                    if (oldTblCategorycategoryIDOfTblproductphotoCollectionNewTblproductphoto != null && !oldTblCategorycategoryIDOfTblproductphotoCollectionNewTblproductphoto.equals(tblcategory)) {
                        oldTblCategorycategoryIDOfTblproductphotoCollectionNewTblproductphoto.getTblproductphotoCollection().remove(tblproductphotoCollectionNewTblproductphoto);
                        oldTblCategorycategoryIDOfTblproductphotoCollectionNewTblproductphoto = em.merge(oldTblCategorycategoryIDOfTblproductphotoCollectionNewTblproductphoto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblcategory.getCategoryID();
                if (findTblcategory(id) == null) {
                    throw new NonexistentEntityException("The tblcategory with id " + id + " no longer exists.");
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
            Tblcategory tblcategory;
            try {
                tblcategory = em.getReference(Tblcategory.class, id);
                tblcategory.getCategoryID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblcategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbltracking> tbltrackingCollectionOrphanCheck = tblcategory.getTbltrackingCollection();
            for (Tbltracking tbltrackingCollectionOrphanCheckTbltracking : tbltrackingCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblcategory (" + tblcategory + ") cannot be destroyed since the Tbltracking " + tbltrackingCollectionOrphanCheckTbltracking + " in its tbltrackingCollection field has a non-nullable tblcategory field.");
            }
            Collection<Tblideabookphoto> tblideabookphotoCollectionOrphanCheck = tblcategory.getTblideabookphotoCollection();
            for (Tblideabookphoto tblideabookphotoCollectionOrphanCheckTblideabookphoto : tblideabookphotoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblcategory (" + tblcategory + ") cannot be destroyed since the Tblideabookphoto " + tblideabookphotoCollectionOrphanCheckTblideabookphoto + " in its tblideabookphotoCollection field has a non-nullable tblCategorycategoryID field.");
            }
            Collection<Tblproductphoto> tblproductphotoCollectionOrphanCheck = tblcategory.getTblproductphotoCollection();
            for (Tblproductphoto tblproductphotoCollectionOrphanCheckTblproductphoto : tblproductphotoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblcategory (" + tblcategory + ") cannot be destroyed since the Tblproductphoto " + tblproductphotoCollectionOrphanCheckTblproductphoto + " in its tblproductphotoCollection field has a non-nullable tblCategorycategoryID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tblcategory);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblcategory> findTblcategoryEntities() {
        return findTblcategoryEntities(true, -1, -1);
    }

    public List<Tblcategory> findTblcategoryEntities(int maxResults, int firstResult) {
        return findTblcategoryEntities(false, maxResults, firstResult);
    }

    private List<Tblcategory> findTblcategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblcategory.class));
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

    public Tblcategory findTblcategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblcategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblcategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblcategory> rt = cq.from(Tblcategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
