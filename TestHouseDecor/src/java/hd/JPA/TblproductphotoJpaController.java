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
import hd.entity.Tblproduct;
import hd.entity.Tblproductphoto;
import hd.entity.Tblstyle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblproductphotoJpaController implements Serializable {

    public TblproductphotoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblproductphoto tblproductphoto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategory tblCategorycategoryID = tblproductphoto.getTblCategorycategoryID();
            if (tblCategorycategoryID != null) {
                tblCategorycategoryID = em.getReference(tblCategorycategoryID.getClass(), tblCategorycategoryID.getCategoryID());
                tblproductphoto.setTblCategorycategoryID(tblCategorycategoryID);
            }
            Tblproduct tblProductproductID = tblproductphoto.getTblProductproductID();
            if (tblProductproductID != null) {
                tblProductproductID = em.getReference(tblProductproductID.getClass(), tblProductproductID.getProductID());
                tblproductphoto.setTblProductproductID(tblProductproductID);
            }
            Tblstyle tblStylestyleID = tblproductphoto.getTblStylestyleID();
            if (tblStylestyleID != null) {
                tblStylestyleID = em.getReference(tblStylestyleID.getClass(), tblStylestyleID.getStyleID());
                tblproductphoto.setTblStylestyleID(tblStylestyleID);
            }
            em.persist(tblproductphoto);
            if (tblCategorycategoryID != null) {
                tblCategorycategoryID.getTblproductphotoCollection().add(tblproductphoto);
                tblCategorycategoryID = em.merge(tblCategorycategoryID);
            }
            if (tblProductproductID != null) {
                tblProductproductID.getTblproductphotoCollection().add(tblproductphoto);
                tblProductproductID = em.merge(tblProductproductID);
            }
            if (tblStylestyleID != null) {
                tblStylestyleID.getTblproductphotoCollection().add(tblproductphoto);
                tblStylestyleID = em.merge(tblStylestyleID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblproductphoto tblproductphoto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblproductphoto persistentTblproductphoto = em.find(Tblproductphoto.class, tblproductphoto.getProductPhotoID());
            Tblcategory tblCategorycategoryIDOld = persistentTblproductphoto.getTblCategorycategoryID();
            Tblcategory tblCategorycategoryIDNew = tblproductphoto.getTblCategorycategoryID();
            Tblproduct tblProductproductIDOld = persistentTblproductphoto.getTblProductproductID();
            Tblproduct tblProductproductIDNew = tblproductphoto.getTblProductproductID();
            Tblstyle tblStylestyleIDOld = persistentTblproductphoto.getTblStylestyleID();
            Tblstyle tblStylestyleIDNew = tblproductphoto.getTblStylestyleID();
            if (tblCategorycategoryIDNew != null) {
                tblCategorycategoryIDNew = em.getReference(tblCategorycategoryIDNew.getClass(), tblCategorycategoryIDNew.getCategoryID());
                tblproductphoto.setTblCategorycategoryID(tblCategorycategoryIDNew);
            }
            if (tblProductproductIDNew != null) {
                tblProductproductIDNew = em.getReference(tblProductproductIDNew.getClass(), tblProductproductIDNew.getProductID());
                tblproductphoto.setTblProductproductID(tblProductproductIDNew);
            }
            if (tblStylestyleIDNew != null) {
                tblStylestyleIDNew = em.getReference(tblStylestyleIDNew.getClass(), tblStylestyleIDNew.getStyleID());
                tblproductphoto.setTblStylestyleID(tblStylestyleIDNew);
            }
            tblproductphoto = em.merge(tblproductphoto);
            if (tblCategorycategoryIDOld != null && !tblCategorycategoryIDOld.equals(tblCategorycategoryIDNew)) {
                tblCategorycategoryIDOld.getTblproductphotoCollection().remove(tblproductphoto);
                tblCategorycategoryIDOld = em.merge(tblCategorycategoryIDOld);
            }
            if (tblCategorycategoryIDNew != null && !tblCategorycategoryIDNew.equals(tblCategorycategoryIDOld)) {
                tblCategorycategoryIDNew.getTblproductphotoCollection().add(tblproductphoto);
                tblCategorycategoryIDNew = em.merge(tblCategorycategoryIDNew);
            }
            if (tblProductproductIDOld != null && !tblProductproductIDOld.equals(tblProductproductIDNew)) {
                tblProductproductIDOld.getTblproductphotoCollection().remove(tblproductphoto);
                tblProductproductIDOld = em.merge(tblProductproductIDOld);
            }
            if (tblProductproductIDNew != null && !tblProductproductIDNew.equals(tblProductproductIDOld)) {
                tblProductproductIDNew.getTblproductphotoCollection().add(tblproductphoto);
                tblProductproductIDNew = em.merge(tblProductproductIDNew);
            }
            if (tblStylestyleIDOld != null && !tblStylestyleIDOld.equals(tblStylestyleIDNew)) {
                tblStylestyleIDOld.getTblproductphotoCollection().remove(tblproductphoto);
                tblStylestyleIDOld = em.merge(tblStylestyleIDOld);
            }
            if (tblStylestyleIDNew != null && !tblStylestyleIDNew.equals(tblStylestyleIDOld)) {
                tblStylestyleIDNew.getTblproductphotoCollection().add(tblproductphoto);
                tblStylestyleIDNew = em.merge(tblStylestyleIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblproductphoto.getProductPhotoID();
                if (findTblproductphoto(id) == null) {
                    throw new NonexistentEntityException("The tblproductphoto with id " + id + " no longer exists.");
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
            Tblproductphoto tblproductphoto;
            try {
                tblproductphoto = em.getReference(Tblproductphoto.class, id);
                tblproductphoto.getProductPhotoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblproductphoto with id " + id + " no longer exists.", enfe);
            }
            Tblcategory tblCategorycategoryID = tblproductphoto.getTblCategorycategoryID();
            if (tblCategorycategoryID != null) {
                tblCategorycategoryID.getTblproductphotoCollection().remove(tblproductphoto);
                tblCategorycategoryID = em.merge(tblCategorycategoryID);
            }
            Tblproduct tblProductproductID = tblproductphoto.getTblProductproductID();
            if (tblProductproductID != null) {
                tblProductproductID.getTblproductphotoCollection().remove(tblproductphoto);
                tblProductproductID = em.merge(tblProductproductID);
            }
            Tblstyle tblStylestyleID = tblproductphoto.getTblStylestyleID();
            if (tblStylestyleID != null) {
                tblStylestyleID.getTblproductphotoCollection().remove(tblproductphoto);
                tblStylestyleID = em.merge(tblStylestyleID);
            }
            em.remove(tblproductphoto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblproductphoto> findTblproductphotoEntities() {
        return findTblproductphotoEntities(true, -1, -1);
    }

    public List<Tblproductphoto> findTblproductphotoEntities(int maxResults, int firstResult) {
        return findTblproductphotoEntities(false, maxResults, firstResult);
    }

    private List<Tblproductphoto> findTblproductphotoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblproductphoto.class));
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

    public Tblproductphoto findTblproductphoto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblproductphoto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblproductphotoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblproductphoto> rt = cq.from(Tblproductphoto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
