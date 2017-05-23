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
import hd.entity.Tlcity;
import hd.entity.role;
import java.util.ArrayList;
import java.util.List;
import hd.entity.tracking;
import hd.entity.project;
import hd.entity.story;
import hd.entity.orders;
import hd.entity.ideabook;
import hd.entity.user;
import hd.model.exceptions.IllegalOrphanException;
import hd.model.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author cuk3t
 */
public class userJpaController implements Serializable {

    public userJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(user user) {
        if (user.getRoleList() == null) {
            user.setRoleList(new ArrayList<role>());
        }
        if (user.getTrackingList() == null) {
            user.setTrackingList(new ArrayList<tracking>());
        }
        if (user.getProjectList() == null) {
            user.setProjectList(new ArrayList<project>());
        }
        if (user.getStoryList() == null) {
            user.setStoryList(new ArrayList<story>());
        }
        if (user.getOrdersList() == null) {
            user.setOrdersList(new ArrayList<orders>());
        }
        if (user.getIdeabookList() == null) {
            user.setIdeabookList(new ArrayList<ideabook>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            professional professional = user.getProfessional();
            if (professional != null) {
                professional = em.getReference(professional.getClass(), professional.getUserID());
                user.setProfessional(professional);
            }
            Tlcity cityCode = user.getCityCode();
            if (cityCode != null) {
                cityCode = em.getReference(cityCode.getClass(), cityCode.getCityCode());
                user.setCityCode(cityCode);
            }
            List<role> attachedRoleList = new ArrayList<role>();
            for (role roleListroleToAttach : user.getRoleList()) {
                roleListroleToAttach = em.getReference(roleListroleToAttach.getClass(), roleListroleToAttach.getRoleID());
                attachedRoleList.add(roleListroleToAttach);
            }
            user.setRoleList(attachedRoleList);
            List<tracking> attachedTrackingList = new ArrayList<tracking>();
            for (tracking trackingListtrackingToAttach : user.getTrackingList()) {
                trackingListtrackingToAttach = em.getReference(trackingListtrackingToAttach.getClass(), trackingListtrackingToAttach.getTrackingPK());
                attachedTrackingList.add(trackingListtrackingToAttach);
            }
            user.setTrackingList(attachedTrackingList);
            List<project> attachedProjectList = new ArrayList<project>();
            for (project projectListprojectToAttach : user.getProjectList()) {
                projectListprojectToAttach = em.getReference(projectListprojectToAttach.getClass(), projectListprojectToAttach.getProjectID());
                attachedProjectList.add(projectListprojectToAttach);
            }
            user.setProjectList(attachedProjectList);
            List<story> attachedStoryList = new ArrayList<story>();
            for (story storyListstoryToAttach : user.getStoryList()) {
                storyListstoryToAttach = em.getReference(storyListstoryToAttach.getClass(), storyListstoryToAttach.getStoryID());
                attachedStoryList.add(storyListstoryToAttach);
            }
            user.setStoryList(attachedStoryList);
            List<orders> attachedOrdersList = new ArrayList<orders>();
            for (orders ordersListordersToAttach : user.getOrdersList()) {
                ordersListordersToAttach = em.getReference(ordersListordersToAttach.getClass(), ordersListordersToAttach.getOrderID());
                attachedOrdersList.add(ordersListordersToAttach);
            }
            user.setOrdersList(attachedOrdersList);
            List<ideabook> attachedIdeabookList = new ArrayList<ideabook>();
            for (ideabook ideabookListideabookToAttach : user.getIdeabookList()) {
                ideabookListideabookToAttach = em.getReference(ideabookListideabookToAttach.getClass(), ideabookListideabookToAttach.getIdeaBookID());
                attachedIdeabookList.add(ideabookListideabookToAttach);
            }
            user.setIdeabookList(attachedIdeabookList);
            em.persist(user);
            if (professional != null) {
                user oldUserOfProfessional = professional.getUser();
                if (oldUserOfProfessional != null) {
                    oldUserOfProfessional.setProfessional(null);
                    oldUserOfProfessional = em.merge(oldUserOfProfessional);
                }
                professional.setUser(user);
                professional = em.merge(professional);
            }
            if (cityCode != null) {
                cityCode.getUserList().add(user);
                cityCode = em.merge(cityCode);
            }
            for (role roleListrole : user.getRoleList()) {
                user oldUserIDOfRoleListrole = roleListrole.getUserID();
                roleListrole.setUserID(user);
                roleListrole = em.merge(roleListrole);
                if (oldUserIDOfRoleListrole != null) {
                    oldUserIDOfRoleListrole.getRoleList().remove(roleListrole);
                    oldUserIDOfRoleListrole = em.merge(oldUserIDOfRoleListrole);
                }
            }
            for (tracking trackingListtracking : user.getTrackingList()) {
                user oldUserOfTrackingListtracking = trackingListtracking.getUser();
                trackingListtracking.setUser(user);
                trackingListtracking = em.merge(trackingListtracking);
                if (oldUserOfTrackingListtracking != null) {
                    oldUserOfTrackingListtracking.getTrackingList().remove(trackingListtracking);
                    oldUserOfTrackingListtracking = em.merge(oldUserOfTrackingListtracking);
                }
            }
            for (project projectListproject : user.getProjectList()) {
                user oldUserIDOfProjectListproject = projectListproject.getUserID();
                projectListproject.setUserID(user);
                projectListproject = em.merge(projectListproject);
                if (oldUserIDOfProjectListproject != null) {
                    oldUserIDOfProjectListproject.getProjectList().remove(projectListproject);
                    oldUserIDOfProjectListproject = em.merge(oldUserIDOfProjectListproject);
                }
            }
            for (story storyListstory : user.getStoryList()) {
                user oldUserIDOfStoryListstory = storyListstory.getUserID();
                storyListstory.setUserID(user);
                storyListstory = em.merge(storyListstory);
                if (oldUserIDOfStoryListstory != null) {
                    oldUserIDOfStoryListstory.getStoryList().remove(storyListstory);
                    oldUserIDOfStoryListstory = em.merge(oldUserIDOfStoryListstory);
                }
            }
            for (orders ordersListorders : user.getOrdersList()) {
                user oldUserIDOfOrdersListorders = ordersListorders.getUserID();
                ordersListorders.setUserID(user);
                ordersListorders = em.merge(ordersListorders);
                if (oldUserIDOfOrdersListorders != null) {
                    oldUserIDOfOrdersListorders.getOrdersList().remove(ordersListorders);
                    oldUserIDOfOrdersListorders = em.merge(oldUserIDOfOrdersListorders);
                }
            }
            for (ideabook ideabookListideabook : user.getIdeabookList()) {
                user oldUserIDOfIdeabookListideabook = ideabookListideabook.getUserID();
                ideabookListideabook.setUserID(user);
                ideabookListideabook = em.merge(ideabookListideabook);
                if (oldUserIDOfIdeabookListideabook != null) {
                    oldUserIDOfIdeabookListideabook.getIdeabookList().remove(ideabookListideabook);
                    oldUserIDOfIdeabookListideabook = em.merge(oldUserIDOfIdeabookListideabook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(user user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user persistentuser = em.find(user.class, user.getUserID());
            professional professionalOld = persistentuser.getProfessional();
            professional professionalNew = user.getProfessional();
            Tlcity cityCodeOld = persistentuser.getCityCode();
            Tlcity cityCodeNew = user.getCityCode();
            List<role> roleListOld = persistentuser.getRoleList();
            List<role> roleListNew = user.getRoleList();
            List<tracking> trackingListOld = persistentuser.getTrackingList();
            List<tracking> trackingListNew = user.getTrackingList();
            List<project> projectListOld = persistentuser.getProjectList();
            List<project> projectListNew = user.getProjectList();
            List<story> storyListOld = persistentuser.getStoryList();
            List<story> storyListNew = user.getStoryList();
            List<orders> ordersListOld = persistentuser.getOrdersList();
            List<orders> ordersListNew = user.getOrdersList();
            List<ideabook> ideabookListOld = persistentuser.getIdeabookList();
            List<ideabook> ideabookListNew = user.getIdeabookList();
            List<String> illegalOrphanMessages = null;
            if (professionalOld != null && !professionalOld.equals(professionalNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain professional " + professionalOld + " since its user field is not nullable.");
            }
            for (role roleListOldrole : roleListOld) {
                if (!roleListNew.contains(roleListOldrole)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain role " + roleListOldrole + " since its userID field is not nullable.");
                }
            }
            for (tracking trackingListOldtracking : trackingListOld) {
                if (!trackingListNew.contains(trackingListOldtracking)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain tracking " + trackingListOldtracking + " since its user field is not nullable.");
                }
            }
            for (project projectListOldproject : projectListOld) {
                if (!projectListNew.contains(projectListOldproject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain project " + projectListOldproject + " since its userID field is not nullable.");
                }
            }
            for (story storyListOldstory : storyListOld) {
                if (!storyListNew.contains(storyListOldstory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain story " + storyListOldstory + " since its userID field is not nullable.");
                }
            }
            for (orders ordersListOldorders : ordersListOld) {
                if (!ordersListNew.contains(ordersListOldorders)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain orders " + ordersListOldorders + " since its userID field is not nullable.");
                }
            }
            for (ideabook ideabookListOldideabook : ideabookListOld) {
                if (!ideabookListNew.contains(ideabookListOldideabook)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ideabook " + ideabookListOldideabook + " since its userID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (professionalNew != null) {
                professionalNew = em.getReference(professionalNew.getClass(), professionalNew.getUserID());
                user.setProfessional(professionalNew);
            }
            if (cityCodeNew != null) {
                cityCodeNew = em.getReference(cityCodeNew.getClass(), cityCodeNew.getCityCode());
                user.setCityCode(cityCodeNew);
            }
            List<role> attachedRoleListNew = new ArrayList<role>();
            for (role roleListNewroleToAttach : roleListNew) {
                roleListNewroleToAttach = em.getReference(roleListNewroleToAttach.getClass(), roleListNewroleToAttach.getRoleID());
                attachedRoleListNew.add(roleListNewroleToAttach);
            }
            roleListNew = attachedRoleListNew;
            user.setRoleList(roleListNew);
            List<tracking> attachedTrackingListNew = new ArrayList<tracking>();
            for (tracking trackingListNewtrackingToAttach : trackingListNew) {
                trackingListNewtrackingToAttach = em.getReference(trackingListNewtrackingToAttach.getClass(), trackingListNewtrackingToAttach.getTrackingPK());
                attachedTrackingListNew.add(trackingListNewtrackingToAttach);
            }
            trackingListNew = attachedTrackingListNew;
            user.setTrackingList(trackingListNew);
            List<project> attachedProjectListNew = new ArrayList<project>();
            for (project projectListNewprojectToAttach : projectListNew) {
                projectListNewprojectToAttach = em.getReference(projectListNewprojectToAttach.getClass(), projectListNewprojectToAttach.getProjectID());
                attachedProjectListNew.add(projectListNewprojectToAttach);
            }
            projectListNew = attachedProjectListNew;
            user.setProjectList(projectListNew);
            List<story> attachedStoryListNew = new ArrayList<story>();
            for (story storyListNewstoryToAttach : storyListNew) {
                storyListNewstoryToAttach = em.getReference(storyListNewstoryToAttach.getClass(), storyListNewstoryToAttach.getStoryID());
                attachedStoryListNew.add(storyListNewstoryToAttach);
            }
            storyListNew = attachedStoryListNew;
            user.setStoryList(storyListNew);
            List<orders> attachedOrdersListNew = new ArrayList<orders>();
            for (orders ordersListNewordersToAttach : ordersListNew) {
                ordersListNewordersToAttach = em.getReference(ordersListNewordersToAttach.getClass(), ordersListNewordersToAttach.getOrderID());
                attachedOrdersListNew.add(ordersListNewordersToAttach);
            }
            ordersListNew = attachedOrdersListNew;
            user.setOrdersList(ordersListNew);
            List<ideabook> attachedIdeabookListNew = new ArrayList<ideabook>();
            for (ideabook ideabookListNewideabookToAttach : ideabookListNew) {
                ideabookListNewideabookToAttach = em.getReference(ideabookListNewideabookToAttach.getClass(), ideabookListNewideabookToAttach.getIdeaBookID());
                attachedIdeabookListNew.add(ideabookListNewideabookToAttach);
            }
            ideabookListNew = attachedIdeabookListNew;
            user.setIdeabookList(ideabookListNew);
            user = em.merge(user);
            if (professionalNew != null && !professionalNew.equals(professionalOld)) {
                user oldUserOfProfessional = professionalNew.getUser();
                if (oldUserOfProfessional != null) {
                    oldUserOfProfessional.setProfessional(null);
                    oldUserOfProfessional = em.merge(oldUserOfProfessional);
                }
                professionalNew.setUser(user);
                professionalNew = em.merge(professionalNew);
            }
            if (cityCodeOld != null && !cityCodeOld.equals(cityCodeNew)) {
                cityCodeOld.getUserList().remove(user);
                cityCodeOld = em.merge(cityCodeOld);
            }
            if (cityCodeNew != null && !cityCodeNew.equals(cityCodeOld)) {
                cityCodeNew.getUserList().add(user);
                cityCodeNew = em.merge(cityCodeNew);
            }
            for (role roleListNewrole : roleListNew) {
                if (!roleListOld.contains(roleListNewrole)) {
                    user oldUserIDOfRoleListNewrole = roleListNewrole.getUserID();
                    roleListNewrole.setUserID(user);
                    roleListNewrole = em.merge(roleListNewrole);
                    if (oldUserIDOfRoleListNewrole != null && !oldUserIDOfRoleListNewrole.equals(user)) {
                        oldUserIDOfRoleListNewrole.getRoleList().remove(roleListNewrole);
                        oldUserIDOfRoleListNewrole = em.merge(oldUserIDOfRoleListNewrole);
                    }
                }
            }
            for (tracking trackingListNewtracking : trackingListNew) {
                if (!trackingListOld.contains(trackingListNewtracking)) {
                    user oldUserOfTrackingListNewtracking = trackingListNewtracking.getUser();
                    trackingListNewtracking.setUser(user);
                    trackingListNewtracking = em.merge(trackingListNewtracking);
                    if (oldUserOfTrackingListNewtracking != null && !oldUserOfTrackingListNewtracking.equals(user)) {
                        oldUserOfTrackingListNewtracking.getTrackingList().remove(trackingListNewtracking);
                        oldUserOfTrackingListNewtracking = em.merge(oldUserOfTrackingListNewtracking);
                    }
                }
            }
            for (project projectListNewproject : projectListNew) {
                if (!projectListOld.contains(projectListNewproject)) {
                    user oldUserIDOfProjectListNewproject = projectListNewproject.getUserID();
                    projectListNewproject.setUserID(user);
                    projectListNewproject = em.merge(projectListNewproject);
                    if (oldUserIDOfProjectListNewproject != null && !oldUserIDOfProjectListNewproject.equals(user)) {
                        oldUserIDOfProjectListNewproject.getProjectList().remove(projectListNewproject);
                        oldUserIDOfProjectListNewproject = em.merge(oldUserIDOfProjectListNewproject);
                    }
                }
            }
            for (story storyListNewstory : storyListNew) {
                if (!storyListOld.contains(storyListNewstory)) {
                    user oldUserIDOfStoryListNewstory = storyListNewstory.getUserID();
                    storyListNewstory.setUserID(user);
                    storyListNewstory = em.merge(storyListNewstory);
                    if (oldUserIDOfStoryListNewstory != null && !oldUserIDOfStoryListNewstory.equals(user)) {
                        oldUserIDOfStoryListNewstory.getStoryList().remove(storyListNewstory);
                        oldUserIDOfStoryListNewstory = em.merge(oldUserIDOfStoryListNewstory);
                    }
                }
            }
            for (orders ordersListNeworders : ordersListNew) {
                if (!ordersListOld.contains(ordersListNeworders)) {
                    user oldUserIDOfOrdersListNeworders = ordersListNeworders.getUserID();
                    ordersListNeworders.setUserID(user);
                    ordersListNeworders = em.merge(ordersListNeworders);
                    if (oldUserIDOfOrdersListNeworders != null && !oldUserIDOfOrdersListNeworders.equals(user)) {
                        oldUserIDOfOrdersListNeworders.getOrdersList().remove(ordersListNeworders);
                        oldUserIDOfOrdersListNeworders = em.merge(oldUserIDOfOrdersListNeworders);
                    }
                }
            }
            for (ideabook ideabookListNewideabook : ideabookListNew) {
                if (!ideabookListOld.contains(ideabookListNewideabook)) {
                    user oldUserIDOfIdeabookListNewideabook = ideabookListNewideabook.getUserID();
                    ideabookListNewideabook.setUserID(user);
                    ideabookListNewideabook = em.merge(ideabookListNewideabook);
                    if (oldUserIDOfIdeabookListNewideabook != null && !oldUserIDOfIdeabookListNewideabook.equals(user)) {
                        oldUserIDOfIdeabookListNewideabook.getIdeabookList().remove(ideabookListNewideabook);
                        oldUserIDOfIdeabookListNewideabook = em.merge(oldUserIDOfIdeabookListNewideabook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserID();
                if (finduser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            user user;
            try {
                user = em.getReference(user.class, id);
                user.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            professional professionalOrphanCheck = user.getProfessional();
            if (professionalOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the professional " + professionalOrphanCheck + " in its professional field has a non-nullable user field.");
            }
            List<role> roleListOrphanCheck = user.getRoleList();
            for (role roleListOrphanCheckrole : roleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the role " + roleListOrphanCheckrole + " in its roleList field has a non-nullable userID field.");
            }
            List<tracking> trackingListOrphanCheck = user.getTrackingList();
            for (tracking trackingListOrphanChecktracking : trackingListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the tracking " + trackingListOrphanChecktracking + " in its trackingList field has a non-nullable user field.");
            }
            List<project> projectListOrphanCheck = user.getProjectList();
            for (project projectListOrphanCheckproject : projectListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the project " + projectListOrphanCheckproject + " in its projectList field has a non-nullable userID field.");
            }
            List<story> storyListOrphanCheck = user.getStoryList();
            for (story storyListOrphanCheckstory : storyListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the story " + storyListOrphanCheckstory + " in its storyList field has a non-nullable userID field.");
            }
            List<orders> ordersListOrphanCheck = user.getOrdersList();
            for (orders ordersListOrphanCheckorders : ordersListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the orders " + ordersListOrphanCheckorders + " in its ordersList field has a non-nullable userID field.");
            }
            List<ideabook> ideabookListOrphanCheck = user.getIdeabookList();
            for (ideabook ideabookListOrphanCheckideabook : ideabookListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This user (" + user + ") cannot be destroyed since the ideabook " + ideabookListOrphanCheckideabook + " in its ideabookList field has a non-nullable userID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tlcity cityCode = user.getCityCode();
            if (cityCode != null) {
                cityCode.getUserList().remove(user);
                cityCode = em.merge(cityCode);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<user> finduserEntities() {
        return finduserEntities(true, -1, -1);
    }

    public List<user> finduserEntities(int maxResults, int firstResult) {
        return finduserEntities(false, maxResults, firstResult);
    }

    private List<user> finduserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(user.class));
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

    public user finduser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(user.class, id);
        } finally {
            em.close();
        }
    }

    public int getuserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<user> rt = cq.from(user.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
