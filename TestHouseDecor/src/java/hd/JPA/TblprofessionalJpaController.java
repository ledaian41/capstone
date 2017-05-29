/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.IllegalOrphanException;
import hd.JPA.exceptions.NonexistentEntityException;
import hd.JPA.exceptions.PreexistingEntityException;
import hd.entity.Tblprofessional;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbltypeofwork;
import hd.entity.Tbluser;
import hd.entity.Tblseller;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblprofessionalJpaController implements Serializable {

    public TblprofessionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblprofessional tblprofessional) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Tbluser tbluserOrphanCheck = tblprofessional.getTbluser();
        if (tbluserOrphanCheck != null) {
            Tblprofessional oldTblprofessionalOfTbluser = tbluserOrphanCheck.getTblprofessional();
            if (oldTblprofessionalOfTbluser != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Tbluser " + tbluserOrphanCheck + " already has an item of type Tblprofessional whose tbluser column cannot be null. Please make another selection for the tbluser field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbltypeofwork tblTypeOfWorkid = tblprofessional.getTblTypeOfWorkid();
            if (tblTypeOfWorkid != null) {
                tblTypeOfWorkid = em.getReference(tblTypeOfWorkid.getClass(), tblTypeOfWorkid.getId());
                tblprofessional.setTblTypeOfWorkid(tblTypeOfWorkid);
            }
            Tbluser tbluser = tblprofessional.getTbluser();
            if (tbluser != null) {
                tbluser = em.getReference(tbluser.getClass(), tbluser.getUserID());
                tblprofessional.setTbluser(tbluser);
            }
            Tblseller tblseller = tblprofessional.getTblseller();
            if (tblseller != null) {
                tblseller = em.getReference(tblseller.getClass(), tblseller.getUserID());
                tblprofessional.setTblseller(tblseller);
            }
            em.persist(tblprofessional);
            if (tblTypeOfWorkid != null) {
                tblTypeOfWorkid.getTblprofessionalCollection().add(tblprofessional);
                tblTypeOfWorkid = em.merge(tblTypeOfWorkid);
            }
            if (tbluser != null) {
                tbluser.setTblprofessional(tblprofessional);
                tbluser = em.merge(tbluser);
            }
            if (tblseller != null) {
                Tblprofessional oldTblprofessionalOfTblseller = tblseller.getTblprofessional();
                if (oldTblprofessionalOfTblseller != null) {
                    oldTblprofessionalOfTblseller.setTblseller(null);
                    oldTblprofessionalOfTblseller = em.merge(oldTblprofessionalOfTblseller);
                }
                tblseller.setTblprofessional(tblprofessional);
                tblseller = em.merge(tblseller);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTblprofessional(tblprofessional.getUserID()) != null) {
                throw new PreexistingEntityException("Tblprofessional " + tblprofessional + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblprofessional tblprofessional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblprofessional persistentTblprofessional = em.find(Tblprofessional.class, tblprofessional.getUserID());
            Tbltypeofwork tblTypeOfWorkidOld = persistentTblprofessional.getTblTypeOfWorkid();
            Tbltypeofwork tblTypeOfWorkidNew = tblprofessional.getTblTypeOfWorkid();
            Tbluser tbluserOld = persistentTblprofessional.getTbluser();
            Tbluser tbluserNew = tblprofessional.getTbluser();
            Tblseller tblsellerOld = persistentTblprofessional.getTblseller();
            Tblseller tblsellerNew = tblprofessional.getTblseller();
            List<String> illegalOrphanMessages = null;
            if (tbluserNew != null && !tbluserNew.equals(tbluserOld)) {
                Tblprofessional oldTblprofessionalOfTbluser = tbluserNew.getTblprofessional();
                if (oldTblprofessionalOfTbluser != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Tbluser " + tbluserNew + " already has an item of type Tblprofessional whose tbluser column cannot be null. Please make another selection for the tbluser field.");
                }
            }
            if (tblsellerOld != null && !tblsellerOld.equals(tblsellerNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Tblseller " + tblsellerOld + " since its tblprofessional field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tblTypeOfWorkidNew != null) {
                tblTypeOfWorkidNew = em.getReference(tblTypeOfWorkidNew.getClass(), tblTypeOfWorkidNew.getId());
                tblprofessional.setTblTypeOfWorkid(tblTypeOfWorkidNew);
            }
            if (tbluserNew != null) {
                tbluserNew = em.getReference(tbluserNew.getClass(), tbluserNew.getUserID());
                tblprofessional.setTbluser(tbluserNew);
            }
            if (tblsellerNew != null) {
                tblsellerNew = em.getReference(tblsellerNew.getClass(), tblsellerNew.getUserID());
                tblprofessional.setTblseller(tblsellerNew);
            }
            tblprofessional = em.merge(tblprofessional);
            if (tblTypeOfWorkidOld != null && !tblTypeOfWorkidOld.equals(tblTypeOfWorkidNew)) {
                tblTypeOfWorkidOld.getTblprofessionalCollection().remove(tblprofessional);
                tblTypeOfWorkidOld = em.merge(tblTypeOfWorkidOld);
            }
            if (tblTypeOfWorkidNew != null && !tblTypeOfWorkidNew.equals(tblTypeOfWorkidOld)) {
                tblTypeOfWorkidNew.getTblprofessionalCollection().add(tblprofessional);
                tblTypeOfWorkidNew = em.merge(tblTypeOfWorkidNew);
            }
            if (tbluserOld != null && !tbluserOld.equals(tbluserNew)) {
                tbluserOld.setTblprofessional(null);
                tbluserOld = em.merge(tbluserOld);
            }
            if (tbluserNew != null && !tbluserNew.equals(tbluserOld)) {
                tbluserNew.setTblprofessional(tblprofessional);
                tbluserNew = em.merge(tbluserNew);
            }
            if (tblsellerNew != null && !tblsellerNew.equals(tblsellerOld)) {
                Tblprofessional oldTblprofessionalOfTblseller = tblsellerNew.getTblprofessional();
                if (oldTblprofessionalOfTblseller != null) {
                    oldTblprofessionalOfTblseller.setTblseller(null);
                    oldTblprofessionalOfTblseller = em.merge(oldTblprofessionalOfTblseller);
                }
                tblsellerNew.setTblprofessional(tblprofessional);
                tblsellerNew = em.merge(tblsellerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblprofessional.getUserID();
                if (findTblprofessional(id) == null) {
                    throw new NonexistentEntityException("The tblprofessional with id " + id + " no longer exists.");
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
            Tblprofessional tblprofessional;
            try {
                tblprofessional = em.getReference(Tblprofessional.class, id);
                tblprofessional.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblprofessional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Tblseller tblsellerOrphanCheck = tblprofessional.getTblseller();
            if (tblsellerOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblprofessional (" + tblprofessional + ") cannot be destroyed since the Tblseller " + tblsellerOrphanCheck + " in its tblseller field has a non-nullable tblprofessional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbltypeofwork tblTypeOfWorkid = tblprofessional.getTblTypeOfWorkid();
            if (tblTypeOfWorkid != null) {
                tblTypeOfWorkid.getTblprofessionalCollection().remove(tblprofessional);
                tblTypeOfWorkid = em.merge(tblTypeOfWorkid);
            }
            Tbluser tbluser = tblprofessional.getTbluser();
            if (tbluser != null) {
                tbluser.setTblprofessional(null);
                tbluser = em.merge(tbluser);
            }
            em.remove(tblprofessional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblprofessional> findTblprofessionalEntities() {
        return findTblprofessionalEntities(true, -1, -1);
    }

    public List<Tblprofessional> findTblprofessionalEntities(int maxResults, int firstResult) {
        return findTblprofessionalEntities(false, maxResults, firstResult);
    }

    private List<Tblprofessional> findTblprofessionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblprofessional.class));
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

    public Tblprofessional findTblprofessional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblprofessional.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblprofessionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblprofessional> rt = cq.from(Tblprofessional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
