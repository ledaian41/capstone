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
import hd.entity.Tblprofessional;
import hd.entity.Tbltypeofwork;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TbltypeofworkJpaController implements Serializable {

    public TbltypeofworkJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbltypeofwork tbltypeofwork) {
        if (tbltypeofwork.getTblprofessionalCollection() == null) {
            tbltypeofwork.setTblprofessionalCollection(new ArrayList<Tblprofessional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tblprofessional> attachedTblprofessionalCollection = new ArrayList<Tblprofessional>();
            for (Tblprofessional tblprofessionalCollectionTblprofessionalToAttach : tbltypeofwork.getTblprofessionalCollection()) {
                tblprofessionalCollectionTblprofessionalToAttach = em.getReference(tblprofessionalCollectionTblprofessionalToAttach.getClass(), tblprofessionalCollectionTblprofessionalToAttach.getUserID());
                attachedTblprofessionalCollection.add(tblprofessionalCollectionTblprofessionalToAttach);
            }
            tbltypeofwork.setTblprofessionalCollection(attachedTblprofessionalCollection);
            em.persist(tbltypeofwork);
            for (Tblprofessional tblprofessionalCollectionTblprofessional : tbltypeofwork.getTblprofessionalCollection()) {
                Tbltypeofwork oldTblTypeOfWorkidOfTblprofessionalCollectionTblprofessional = tblprofessionalCollectionTblprofessional.getTblTypeOfWorkid();
                tblprofessionalCollectionTblprofessional.setTblTypeOfWorkid(tbltypeofwork);
                tblprofessionalCollectionTblprofessional = em.merge(tblprofessionalCollectionTblprofessional);
                if (oldTblTypeOfWorkidOfTblprofessionalCollectionTblprofessional != null) {
                    oldTblTypeOfWorkidOfTblprofessionalCollectionTblprofessional.getTblprofessionalCollection().remove(tblprofessionalCollectionTblprofessional);
                    oldTblTypeOfWorkidOfTblprofessionalCollectionTblprofessional = em.merge(oldTblTypeOfWorkidOfTblprofessionalCollectionTblprofessional);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbltypeofwork tbltypeofwork) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbltypeofwork persistentTbltypeofwork = em.find(Tbltypeofwork.class, tbltypeofwork.getId());
            Collection<Tblprofessional> tblprofessionalCollectionOld = persistentTbltypeofwork.getTblprofessionalCollection();
            Collection<Tblprofessional> tblprofessionalCollectionNew = tbltypeofwork.getTblprofessionalCollection();
            List<String> illegalOrphanMessages = null;
            for (Tblprofessional tblprofessionalCollectionOldTblprofessional : tblprofessionalCollectionOld) {
                if (!tblprofessionalCollectionNew.contains(tblprofessionalCollectionOldTblprofessional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblprofessional " + tblprofessionalCollectionOldTblprofessional + " since its tblTypeOfWorkid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tblprofessional> attachedTblprofessionalCollectionNew = new ArrayList<Tblprofessional>();
            for (Tblprofessional tblprofessionalCollectionNewTblprofessionalToAttach : tblprofessionalCollectionNew) {
                tblprofessionalCollectionNewTblprofessionalToAttach = em.getReference(tblprofessionalCollectionNewTblprofessionalToAttach.getClass(), tblprofessionalCollectionNewTblprofessionalToAttach.getUserID());
                attachedTblprofessionalCollectionNew.add(tblprofessionalCollectionNewTblprofessionalToAttach);
            }
            tblprofessionalCollectionNew = attachedTblprofessionalCollectionNew;
            tbltypeofwork.setTblprofessionalCollection(tblprofessionalCollectionNew);
            tbltypeofwork = em.merge(tbltypeofwork);
            for (Tblprofessional tblprofessionalCollectionNewTblprofessional : tblprofessionalCollectionNew) {
                if (!tblprofessionalCollectionOld.contains(tblprofessionalCollectionNewTblprofessional)) {
                    Tbltypeofwork oldTblTypeOfWorkidOfTblprofessionalCollectionNewTblprofessional = tblprofessionalCollectionNewTblprofessional.getTblTypeOfWorkid();
                    tblprofessionalCollectionNewTblprofessional.setTblTypeOfWorkid(tbltypeofwork);
                    tblprofessionalCollectionNewTblprofessional = em.merge(tblprofessionalCollectionNewTblprofessional);
                    if (oldTblTypeOfWorkidOfTblprofessionalCollectionNewTblprofessional != null && !oldTblTypeOfWorkidOfTblprofessionalCollectionNewTblprofessional.equals(tbltypeofwork)) {
                        oldTblTypeOfWorkidOfTblprofessionalCollectionNewTblprofessional.getTblprofessionalCollection().remove(tblprofessionalCollectionNewTblprofessional);
                        oldTblTypeOfWorkidOfTblprofessionalCollectionNewTblprofessional = em.merge(oldTblTypeOfWorkidOfTblprofessionalCollectionNewTblprofessional);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbltypeofwork.getId();
                if (findTbltypeofwork(id) == null) {
                    throw new NonexistentEntityException("The tbltypeofwork with id " + id + " no longer exists.");
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
            Tbltypeofwork tbltypeofwork;
            try {
                tbltypeofwork = em.getReference(Tbltypeofwork.class, id);
                tbltypeofwork.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbltypeofwork with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblprofessional> tblprofessionalCollectionOrphanCheck = tbltypeofwork.getTblprofessionalCollection();
            for (Tblprofessional tblprofessionalCollectionOrphanCheckTblprofessional : tblprofessionalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbltypeofwork (" + tbltypeofwork + ") cannot be destroyed since the Tblprofessional " + tblprofessionalCollectionOrphanCheckTblprofessional + " in its tblprofessionalCollection field has a non-nullable tblTypeOfWorkid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbltypeofwork);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbltypeofwork> findTbltypeofworkEntities() {
        return findTbltypeofworkEntities(true, -1, -1);
    }

    public List<Tbltypeofwork> findTbltypeofworkEntities(int maxResults, int firstResult) {
        return findTbltypeofworkEntities(false, maxResults, firstResult);
    }

    private List<Tbltypeofwork> findTbltypeofworkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbltypeofwork.class));
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

    public Tbltypeofwork findTbltypeofwork(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbltypeofwork.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbltypeofworkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbltypeofwork> rt = cq.from(Tbltypeofwork.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
