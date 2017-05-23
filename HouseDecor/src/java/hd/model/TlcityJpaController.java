/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.model;

import hd.entity.Tlcity;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hd.entity.user;
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
public class TlcityJpaController implements Serializable {

    public TlcityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tlcity tlcity) throws PreexistingEntityException, Exception {
        if (tlcity.getUserList() == null) {
            tlcity.setUserList(new ArrayList<user>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<user> attachedUserList = new ArrayList<user>();
            for (user userListuserToAttach : tlcity.getUserList()) {
                userListuserToAttach = em.getReference(userListuserToAttach.getClass(), userListuserToAttach.getUserID());
                attachedUserList.add(userListuserToAttach);
            }
            tlcity.setUserList(attachedUserList);
            em.persist(tlcity);
            for (user userListuser : tlcity.getUserList()) {
                Tlcity oldCityCodeOfUserListuser = userListuser.getCityCode();
                userListuser.setCityCode(tlcity);
                userListuser = em.merge(userListuser);
                if (oldCityCodeOfUserListuser != null) {
                    oldCityCodeOfUserListuser.getUserList().remove(userListuser);
                    oldCityCodeOfUserListuser = em.merge(oldCityCodeOfUserListuser);
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
            List<user> userListOld = persistentTlcity.getUserList();
            List<user> userListNew = tlcity.getUserList();
            List<String> illegalOrphanMessages = null;
            for (user userListOlduser : userListOld) {
                if (!userListNew.contains(userListOlduser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain user " + userListOlduser + " since its cityCode field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<user> attachedUserListNew = new ArrayList<user>();
            for (user userListNewuserToAttach : userListNew) {
                userListNewuserToAttach = em.getReference(userListNewuserToAttach.getClass(), userListNewuserToAttach.getUserID());
                attachedUserListNew.add(userListNewuserToAttach);
            }
            userListNew = attachedUserListNew;
            tlcity.setUserList(userListNew);
            tlcity = em.merge(tlcity);
            for (user userListNewuser : userListNew) {
                if (!userListOld.contains(userListNewuser)) {
                    Tlcity oldCityCodeOfUserListNewuser = userListNewuser.getCityCode();
                    userListNewuser.setCityCode(tlcity);
                    userListNewuser = em.merge(userListNewuser);
                    if (oldCityCodeOfUserListNewuser != null && !oldCityCodeOfUserListNewuser.equals(tlcity)) {
                        oldCityCodeOfUserListNewuser.getUserList().remove(userListNewuser);
                        oldCityCodeOfUserListNewuser = em.merge(oldCityCodeOfUserListNewuser);
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
            List<user> userListOrphanCheck = tlcity.getUserList();
            for (user userListOrphanCheckuser : userListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tlcity (" + tlcity + ") cannot be destroyed since the user " + userListOrphanCheckuser + " in its userList field has a non-nullable cityCode field.");
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
