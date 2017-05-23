/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.model;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.professional;
import hd.entity.typeofwork;
import hd.model.exceptions.IllegalOrphanException;
import hd.model.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cuk3t
 */
public class typeofworkJpaController implements Serializable {

    public typeofworkJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(typeofwork typeofwork) {
        if (typeofwork.getProfessionalList() == null) {
            typeofwork.setProfessionalList(new ArrayList<professional>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<professional> attachedProfessionalList = new ArrayList<professional>();
            for (professional professionalListprofessionalToAttach : typeofwork.getProfessionalList()) {
                professionalListprofessionalToAttach = em.getReference(professionalListprofessionalToAttach.getClass(), professionalListprofessionalToAttach.getUserID());
                attachedProfessionalList.add(professionalListprofessionalToAttach);
            }
            typeofwork.setProfessionalList(attachedProfessionalList);
            em.persist(typeofwork);
            for (professional professionalListprofessional : typeofwork.getProfessionalList()) {
                typeofwork oldTblTypeOfWorkidOfProfessionalListprofessional = professionalListprofessional.getTblTypeOfWorkid();
                professionalListprofessional.setTblTypeOfWorkid(typeofwork);
                professionalListprofessional = em.merge(professionalListprofessional);
                if (oldTblTypeOfWorkidOfProfessionalListprofessional != null) {
                    oldTblTypeOfWorkidOfProfessionalListprofessional.getProfessionalList().remove(professionalListprofessional);
                    oldTblTypeOfWorkidOfProfessionalListprofessional = em.merge(oldTblTypeOfWorkidOfProfessionalListprofessional);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(typeofwork typeofwork) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            typeofwork persistenttypeofwork = em.find(typeofwork.class, typeofwork.getId());
            List<professional> professionalListOld = persistenttypeofwork.getProfessionalList();
            List<professional> professionalListNew = typeofwork.getProfessionalList();
            List<String> illegalOrphanMessages = null;
            for (professional professionalListOldprofessional : professionalListOld) {
                if (!professionalListNew.contains(professionalListOldprofessional)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain professional " + professionalListOldprofessional + " since its tblTypeOfWorkid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<professional> attachedProfessionalListNew = new ArrayList<professional>();
            for (professional professionalListNewprofessionalToAttach : professionalListNew) {
                professionalListNewprofessionalToAttach = em.getReference(professionalListNewprofessionalToAttach.getClass(), professionalListNewprofessionalToAttach.getUserID());
                attachedProfessionalListNew.add(professionalListNewprofessionalToAttach);
            }
            professionalListNew = attachedProfessionalListNew;
            typeofwork.setProfessionalList(professionalListNew);
            typeofwork = em.merge(typeofwork);
            for (professional professionalListNewprofessional : professionalListNew) {
                if (!professionalListOld.contains(professionalListNewprofessional)) {
                    typeofwork oldTblTypeOfWorkidOfProfessionalListNewprofessional = professionalListNewprofessional.getTblTypeOfWorkid();
                    professionalListNewprofessional.setTblTypeOfWorkid(typeofwork);
                    professionalListNewprofessional = em.merge(professionalListNewprofessional);
                    if (oldTblTypeOfWorkidOfProfessionalListNewprofessional != null && !oldTblTypeOfWorkidOfProfessionalListNewprofessional.equals(typeofwork)) {
                        oldTblTypeOfWorkidOfProfessionalListNewprofessional.getProfessionalList().remove(professionalListNewprofessional);
                        oldTblTypeOfWorkidOfProfessionalListNewprofessional = em.merge(oldTblTypeOfWorkidOfProfessionalListNewprofessional);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = typeofwork.getId();
                if (findtypeofwork(id) == null) {
                    throw new NonexistentEntityException("The typeofwork with id " + id + " no longer exists.");
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
            typeofwork typeofwork;
            try {
                typeofwork = em.getReference(typeofwork.class, id);
                typeofwork.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The typeofwork with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<professional> professionalListOrphanCheck = typeofwork.getProfessionalList();
            for (professional professionalListOrphanCheckprofessional : professionalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This typeofwork (" + typeofwork + ") cannot be destroyed since the professional " + professionalListOrphanCheckprofessional + " in its professionalList field has a non-nullable tblTypeOfWorkid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(typeofwork);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<typeofwork> findtypeofworkEntities() {
        return findtypeofworkEntities(true, -1, -1);
    }

    public List<typeofwork> findtypeofworkEntities(int maxResults, int firstResult) {
        return findtypeofworkEntities(false, maxResults, firstResult);
    }

    private List<typeofwork> findtypeofworkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(typeofwork.class));
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

    public typeofwork findtypeofwork(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(typeofwork.class, id);
        } finally {
            em.close();
        }
    }

    public int gettypeofworkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<typeofwork> rt = cq.from(typeofwork.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
