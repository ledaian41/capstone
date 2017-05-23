/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.model;

import hd.entity.professional;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.typeofwork;
import hd.entity.user;
import hd.entity.seller;
import hd.model.exceptions.IllegalOrphanException;
import hd.model.exceptions.NonexistentEntityException;
import hd.model.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cuk3t
 */
public class professionalJpaController implements Serializable {

    public professionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(professional professional) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        user userOrphanCheck = professional.getUser();
        if (userOrphanCheck != null) {
            professional oldProfessionalOfUser = userOrphanCheck.getProfessional();
            if (oldProfessionalOfUser != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The user " + userOrphanCheck + " already has an item of type professional whose user column cannot be null. Please make another selection for the user field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            typeofwork tblTypeOfWorkid = professional.getTblTypeOfWorkid();
            if (tblTypeOfWorkid != null) {
                tblTypeOfWorkid = em.getReference(tblTypeOfWorkid.getClass(), tblTypeOfWorkid.getId());
                professional.setTblTypeOfWorkid(tblTypeOfWorkid);
            }
            user user = professional.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getUserID());
                professional.setUser(user);
            }
            seller seller = professional.getSeller();
            if (seller != null) {
                seller = em.getReference(seller.getClass(), seller.getUserID());
                professional.setSeller(seller);
            }
            em.persist(professional);
            if (tblTypeOfWorkid != null) {
                tblTypeOfWorkid.getProfessionalList().add(professional);
                tblTypeOfWorkid = em.merge(tblTypeOfWorkid);
            }
            if (user != null) {
                user.setProfessional(professional);
                user = em.merge(user);
            }
            if (seller != null) {
                professional oldProfessionalOfSeller = seller.getProfessional();
                if (oldProfessionalOfSeller != null) {
                    oldProfessionalOfSeller.setSeller(null);
                    oldProfessionalOfSeller = em.merge(oldProfessionalOfSeller);
                }
                seller.setProfessional(professional);
                seller = em.merge(seller);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findprofessional(professional.getUserID()) != null) {
                throw new PreexistingEntityException("professional " + professional + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(professional professional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            professional persistentprofessional = em.find(professional.class, professional.getUserID());
            typeofwork tblTypeOfWorkidOld = persistentprofessional.getTblTypeOfWorkid();
            typeofwork tblTypeOfWorkidNew = professional.getTblTypeOfWorkid();
            user userOld = persistentprofessional.getUser();
            user userNew = professional.getUser();
            seller sellerOld = persistentprofessional.getSeller();
            seller sellerNew = professional.getSeller();
            List<String> illegalOrphanMessages = null;
            if (userNew != null && !userNew.equals(userOld)) {
                professional oldProfessionalOfUser = userNew.getProfessional();
                if (oldProfessionalOfUser != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The user " + userNew + " already has an item of type professional whose user column cannot be null. Please make another selection for the user field.");
                }
            }
            if (sellerOld != null && !sellerOld.equals(sellerNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain seller " + sellerOld + " since its professional field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tblTypeOfWorkidNew != null) {
                tblTypeOfWorkidNew = em.getReference(tblTypeOfWorkidNew.getClass(), tblTypeOfWorkidNew.getId());
                professional.setTblTypeOfWorkid(tblTypeOfWorkidNew);
            }
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getUserID());
                professional.setUser(userNew);
            }
            if (sellerNew != null) {
                sellerNew = em.getReference(sellerNew.getClass(), sellerNew.getUserID());
                professional.setSeller(sellerNew);
            }
            professional = em.merge(professional);
            if (tblTypeOfWorkidOld != null && !tblTypeOfWorkidOld.equals(tblTypeOfWorkidNew)) {
                tblTypeOfWorkidOld.getProfessionalList().remove(professional);
                tblTypeOfWorkidOld = em.merge(tblTypeOfWorkidOld);
            }
            if (tblTypeOfWorkidNew != null && !tblTypeOfWorkidNew.equals(tblTypeOfWorkidOld)) {
                tblTypeOfWorkidNew.getProfessionalList().add(professional);
                tblTypeOfWorkidNew = em.merge(tblTypeOfWorkidNew);
            }
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.setProfessional(null);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.setProfessional(professional);
                userNew = em.merge(userNew);
            }
            if (sellerNew != null && !sellerNew.equals(sellerOld)) {
                professional oldProfessionalOfSeller = sellerNew.getProfessional();
                if (oldProfessionalOfSeller != null) {
                    oldProfessionalOfSeller.setSeller(null);
                    oldProfessionalOfSeller = em.merge(oldProfessionalOfSeller);
                }
                sellerNew.setProfessional(professional);
                sellerNew = em.merge(sellerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = professional.getUserID();
                if (findprofessional(id) == null) {
                    throw new NonexistentEntityException("The professional with id " + id + " no longer exists.");
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
            professional professional;
            try {
                professional = em.getReference(professional.class, id);
                professional.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The professional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            seller sellerOrphanCheck = professional.getSeller();
            if (sellerOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This professional (" + professional + ") cannot be destroyed since the seller " + sellerOrphanCheck + " in its seller field has a non-nullable professional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            typeofwork tblTypeOfWorkid = professional.getTblTypeOfWorkid();
            if (tblTypeOfWorkid != null) {
                tblTypeOfWorkid.getProfessionalList().remove(professional);
                tblTypeOfWorkid = em.merge(tblTypeOfWorkid);
            }
            user user = professional.getUser();
            if (user != null) {
                user.setProfessional(null);
                user = em.merge(user);
            }
            em.remove(professional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<professional> findprofessionalEntities() {
        return findprofessionalEntities(true, -1, -1);
    }

    public List<professional> findprofessionalEntities(int maxResults, int firstResult) {
        return findprofessionalEntities(false, maxResults, firstResult);
    }

    private List<professional> findprofessionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(professional.class));
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

    public professional findprofessional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(professional.class, id);
        } finally {
            em.close();
        }
    }

    public int getprofessionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<professional> rt = cq.from(professional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
