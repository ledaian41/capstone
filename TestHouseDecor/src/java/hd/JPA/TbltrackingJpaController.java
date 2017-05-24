/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.NonexistentEntityException;
import hd.JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tblcategory;
import hd.entity.Tbltracking;
import hd.entity.TbltrackingPK;
import hd.entity.Tbluser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TbltrackingJpaController implements Serializable {

    public TbltrackingJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbltracking tbltracking) throws PreexistingEntityException, Exception {
        if (tbltracking.getTbltrackingPK() == null) {
            tbltracking.setTbltrackingPK(new TbltrackingPK());
        }
        tbltracking.getTbltrackingPK().setUserID(tbltracking.getTbluser().getUserID());
        tbltracking.getTbltrackingPK().setTblCategorycategoryID(tbltracking.getTblcategory().getCategoryID());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblcategory tblcategory = tbltracking.getTblcategory();
            if (tblcategory != null) {
                tblcategory = em.getReference(tblcategory.getClass(), tblcategory.getCategoryID());
                tbltracking.setTblcategory(tblcategory);
            }
            Tbluser tbluser = tbltracking.getTbluser();
            if (tbluser != null) {
                tbluser = em.getReference(tbluser.getClass(), tbluser.getUserID());
                tbltracking.setTbluser(tbluser);
            }
            em.persist(tbltracking);
            if (tblcategory != null) {
                tblcategory.getTbltrackingCollection().add(tbltracking);
                tblcategory = em.merge(tblcategory);
            }
            if (tbluser != null) {
                tbluser.getTbltrackingCollection().add(tbltracking);
                tbluser = em.merge(tbluser);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbltracking(tbltracking.getTbltrackingPK()) != null) {
                throw new PreexistingEntityException("Tbltracking " + tbltracking + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbltracking tbltracking) throws NonexistentEntityException, Exception {
        tbltracking.getTbltrackingPK().setUserID(tbltracking.getTbluser().getUserID());
        tbltracking.getTbltrackingPK().setTblCategorycategoryID(tbltracking.getTblcategory().getCategoryID());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbltracking persistentTbltracking = em.find(Tbltracking.class, tbltracking.getTbltrackingPK());
            Tblcategory tblcategoryOld = persistentTbltracking.getTblcategory();
            Tblcategory tblcategoryNew = tbltracking.getTblcategory();
            Tbluser tbluserOld = persistentTbltracking.getTbluser();
            Tbluser tbluserNew = tbltracking.getTbluser();
            if (tblcategoryNew != null) {
                tblcategoryNew = em.getReference(tblcategoryNew.getClass(), tblcategoryNew.getCategoryID());
                tbltracking.setTblcategory(tblcategoryNew);
            }
            if (tbluserNew != null) {
                tbluserNew = em.getReference(tbluserNew.getClass(), tbluserNew.getUserID());
                tbltracking.setTbluser(tbluserNew);
            }
            tbltracking = em.merge(tbltracking);
            if (tblcategoryOld != null && !tblcategoryOld.equals(tblcategoryNew)) {
                tblcategoryOld.getTbltrackingCollection().remove(tbltracking);
                tblcategoryOld = em.merge(tblcategoryOld);
            }
            if (tblcategoryNew != null && !tblcategoryNew.equals(tblcategoryOld)) {
                tblcategoryNew.getTbltrackingCollection().add(tbltracking);
                tblcategoryNew = em.merge(tblcategoryNew);
            }
            if (tbluserOld != null && !tbluserOld.equals(tbluserNew)) {
                tbluserOld.getTbltrackingCollection().remove(tbltracking);
                tbluserOld = em.merge(tbluserOld);
            }
            if (tbluserNew != null && !tbluserNew.equals(tbluserOld)) {
                tbluserNew.getTbltrackingCollection().add(tbltracking);
                tbluserNew = em.merge(tbluserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TbltrackingPK id = tbltracking.getTbltrackingPK();
                if (findTbltracking(id) == null) {
                    throw new NonexistentEntityException("The tbltracking with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TbltrackingPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbltracking tbltracking;
            try {
                tbltracking = em.getReference(Tbltracking.class, id);
                tbltracking.getTbltrackingPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbltracking with id " + id + " no longer exists.", enfe);
            }
            Tblcategory tblcategory = tbltracking.getTblcategory();
            if (tblcategory != null) {
                tblcategory.getTbltrackingCollection().remove(tbltracking);
                tblcategory = em.merge(tblcategory);
            }
            Tbluser tbluser = tbltracking.getTbluser();
            if (tbluser != null) {
                tbluser.getTbltrackingCollection().remove(tbltracking);
                tbluser = em.merge(tbluser);
            }
            em.remove(tbltracking);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbltracking> findTbltrackingEntities() {
        return findTbltrackingEntities(true, -1, -1);
    }

    public List<Tbltracking> findTbltrackingEntities(int maxResults, int firstResult) {
        return findTbltrackingEntities(false, maxResults, firstResult);
    }

    private List<Tbltracking> findTbltrackingEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbltracking.class));
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

    public Tbltracking findTbltracking(TbltrackingPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbltracking.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbltrackingCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbltracking> rt = cq.from(Tbltracking.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
