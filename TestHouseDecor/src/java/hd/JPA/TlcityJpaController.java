/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.JPA;

import hd.JPA.exceptions.IllegalOrphanException;
import hd.JPA.exceptions.NonexistentEntityException;
import hd.JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.Tbluser;
import hd.entity.Tlcity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TlcityJpaController implements Serializable {

    public TlcityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tlcity tlcity) throws PreexistingEntityException, Exception {
        if (tlcity.getTbluserCollection() == null) {
            tlcity.setTbluserCollection(new ArrayList<Tbluser>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbluser> attachedTbluserCollection = new ArrayList<Tbluser>();
            for (Tbluser tbluserCollectionTbluserToAttach : tlcity.getTbluserCollection()) {
                tbluserCollectionTbluserToAttach = em.getReference(tbluserCollectionTbluserToAttach.getClass(), tbluserCollectionTbluserToAttach.getUserID());
                attachedTbluserCollection.add(tbluserCollectionTbluserToAttach);
            }
            tlcity.setTbluserCollection(attachedTbluserCollection);
            em.persist(tlcity);
            for (Tbluser tbluserCollectionTbluser : tlcity.getTbluserCollection()) {
                Tlcity oldCityCodeOfTbluserCollectionTbluser = tbluserCollectionTbluser.getCityCode();
                tbluserCollectionTbluser.setCityCode(tlcity);
                tbluserCollectionTbluser = em.merge(tbluserCollectionTbluser);
                if (oldCityCodeOfTbluserCollectionTbluser != null) {
                    oldCityCodeOfTbluserCollectionTbluser.getTbluserCollection().remove(tbluserCollectionTbluser);
                    oldCityCodeOfTbluserCollectionTbluser = em.merge(oldCityCodeOfTbluserCollectionTbluser);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTlcity(tlcity.getCityCode()) != null) {
                throw new PreexistingEntityException("Tlcity " + tlcity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tlcity tlcity) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tlcity persistentTlcity = em.find(Tlcity.class, tlcity.getCityCode());
            Collection<Tbluser> tbluserCollectionOld = persistentTlcity.getTbluserCollection();
            Collection<Tbluser> tbluserCollectionNew = tlcity.getTbluserCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbluser tbluserCollectionOldTbluser : tbluserCollectionOld) {
                if (!tbluserCollectionNew.contains(tbluserCollectionOldTbluser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbluser " + tbluserCollectionOldTbluser + " since its cityCode field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tbluser> attachedTbluserCollectionNew = new ArrayList<Tbluser>();
            for (Tbluser tbluserCollectionNewTbluserToAttach : tbluserCollectionNew) {
                tbluserCollectionNewTbluserToAttach = em.getReference(tbluserCollectionNewTbluserToAttach.getClass(), tbluserCollectionNewTbluserToAttach.getUserID());
                attachedTbluserCollectionNew.add(tbluserCollectionNewTbluserToAttach);
            }
            tbluserCollectionNew = attachedTbluserCollectionNew;
            tlcity.setTbluserCollection(tbluserCollectionNew);
            tlcity = em.merge(tlcity);
            for (Tbluser tbluserCollectionNewTbluser : tbluserCollectionNew) {
                if (!tbluserCollectionOld.contains(tbluserCollectionNewTbluser)) {
                    Tlcity oldCityCodeOfTbluserCollectionNewTbluser = tbluserCollectionNewTbluser.getCityCode();
                    tbluserCollectionNewTbluser.setCityCode(tlcity);
                    tbluserCollectionNewTbluser = em.merge(tbluserCollectionNewTbluser);
                    if (oldCityCodeOfTbluserCollectionNewTbluser != null && !oldCityCodeOfTbluserCollectionNewTbluser.equals(tlcity)) {
                        oldCityCodeOfTbluserCollectionNewTbluser.getTbluserCollection().remove(tbluserCollectionNewTbluser);
                        oldCityCodeOfTbluserCollectionNewTbluser = em.merge(oldCityCodeOfTbluserCollectionNewTbluser);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tlcity.getCityCode();
                if (findTlcity(id) == null) {
                    throw new NonexistentEntityException("The tlcity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tlcity tlcity;
            try {
                tlcity = em.getReference(Tlcity.class, id);
                tlcity.getCityCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tlcity with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbluser> tbluserCollectionOrphanCheck = tlcity.getTbluserCollection();
            for (Tbluser tbluserCollectionOrphanCheckTbluser : tbluserCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tlcity (" + tlcity + ") cannot be destroyed since the Tbluser " + tbluserCollectionOrphanCheckTbluser + " in its tbluserCollection field has a non-nullable cityCode field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tlcity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tlcity> findTlcityEntities() {
        return findTlcityEntities(true, -1, -1);
    }

    public List<Tlcity> findTlcityEntities(int maxResults, int firstResult) {
        return findTlcityEntities(false, maxResults, firstResult);
    }

    private List<Tlcity> findTlcityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tlcity.class));
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

    public Tlcity findTlcity(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tlcity.class, id);
        } finally {
            em.close();
        }
    }

    public int getTlcityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tlcity> rt = cq.from(Tlcity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
