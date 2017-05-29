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
import hd.entity.Tlcity;
import hd.entity.Tblrole;
import java.util.ArrayList;
import java.util.Collection;
import hd.entity.Tbltracking;
import hd.entity.Tblproject;
import hd.entity.Tblstory;
import hd.entity.Tblorder;
import hd.entity.Tblideabook;
import hd.entity.Tbluser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TbluserJpaController implements Serializable {

    public TbluserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbluser tbluser) {
        if (tbluser.getTblroleCollection() == null) {
            tbluser.setTblroleCollection(new ArrayList<Tblrole>());
        }
        if (tbluser.getTbltrackingCollection() == null) {
            tbluser.setTbltrackingCollection(new ArrayList<Tbltracking>());
        }
        if (tbluser.getTblprojectCollection() == null) {
            tbluser.setTblprojectCollection(new ArrayList<Tblproject>());
        }
        if (tbluser.getTblstoryCollection() == null) {
            tbluser.setTblstoryCollection(new ArrayList<Tblstory>());
        }
        if (tbluser.getTblorderCollection() == null) {
            tbluser.setTblorderCollection(new ArrayList<Tblorder>());
        }
        if (tbluser.getTblideabookCollection() == null) {
            tbluser.setTblideabookCollection(new ArrayList<Tblideabook>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblprofessional tblprofessional = tbluser.getTblprofessional();
            if (tblprofessional != null) {
                tblprofessional = em.getReference(tblprofessional.getClass(), tblprofessional.getUserID());
                tbluser.setTblprofessional(tblprofessional);
            }
            Tlcity cityCode = tbluser.getCityCode();
            if (cityCode != null) {
                cityCode = em.getReference(cityCode.getClass(), cityCode.getCityCode());
                tbluser.setCityCode(cityCode);
            }
            Collection<Tblrole> attachedTblroleCollection = new ArrayList<Tblrole>();
            for (Tblrole tblroleCollectionTblroleToAttach : tbluser.getTblroleCollection()) {
                tblroleCollectionTblroleToAttach = em.getReference(tblroleCollectionTblroleToAttach.getClass(), tblroleCollectionTblroleToAttach.getRoleID());
                attachedTblroleCollection.add(tblroleCollectionTblroleToAttach);
            }
            tbluser.setTblroleCollection(attachedTblroleCollection);
            Collection<Tbltracking> attachedTbltrackingCollection = new ArrayList<Tbltracking>();
            for (Tbltracking tbltrackingCollectionTbltrackingToAttach : tbluser.getTbltrackingCollection()) {
                tbltrackingCollectionTbltrackingToAttach = em.getReference(tbltrackingCollectionTbltrackingToAttach.getClass(), tbltrackingCollectionTbltrackingToAttach.getTbltrackingPK());
                attachedTbltrackingCollection.add(tbltrackingCollectionTbltrackingToAttach);
            }
            tbluser.setTbltrackingCollection(attachedTbltrackingCollection);
            Collection<Tblproject> attachedTblprojectCollection = new ArrayList<Tblproject>();
            for (Tblproject tblprojectCollectionTblprojectToAttach : tbluser.getTblprojectCollection()) {
                tblprojectCollectionTblprojectToAttach = em.getReference(tblprojectCollectionTblprojectToAttach.getClass(), tblprojectCollectionTblprojectToAttach.getProjectID());
                attachedTblprojectCollection.add(tblprojectCollectionTblprojectToAttach);
            }
            tbluser.setTblprojectCollection(attachedTblprojectCollection);
            Collection<Tblstory> attachedTblstoryCollection = new ArrayList<Tblstory>();
            for (Tblstory tblstoryCollectionTblstoryToAttach : tbluser.getTblstoryCollection()) {
                tblstoryCollectionTblstoryToAttach = em.getReference(tblstoryCollectionTblstoryToAttach.getClass(), tblstoryCollectionTblstoryToAttach.getStoryID());
                attachedTblstoryCollection.add(tblstoryCollectionTblstoryToAttach);
            }
            tbluser.setTblstoryCollection(attachedTblstoryCollection);
            Collection<Tblorder> attachedTblorderCollection = new ArrayList<Tblorder>();
            for (Tblorder tblorderCollectionTblorderToAttach : tbluser.getTblorderCollection()) {
                tblorderCollectionTblorderToAttach = em.getReference(tblorderCollectionTblorderToAttach.getClass(), tblorderCollectionTblorderToAttach.getOrderID());
                attachedTblorderCollection.add(tblorderCollectionTblorderToAttach);
            }
            tbluser.setTblorderCollection(attachedTblorderCollection);
            Collection<Tblideabook> attachedTblideabookCollection = new ArrayList<Tblideabook>();
            for (Tblideabook tblideabookCollectionTblideabookToAttach : tbluser.getTblideabookCollection()) {
                tblideabookCollectionTblideabookToAttach = em.getReference(tblideabookCollectionTblideabookToAttach.getClass(), tblideabookCollectionTblideabookToAttach.getIdeaBookID());
                attachedTblideabookCollection.add(tblideabookCollectionTblideabookToAttach);
            }
            tbluser.setTblideabookCollection(attachedTblideabookCollection);
            em.persist(tbluser);
            if (tblprofessional != null) {
                Tbluser oldTbluserOfTblprofessional = tblprofessional.getTbluser();
                if (oldTbluserOfTblprofessional != null) {
                    oldTbluserOfTblprofessional.setTblprofessional(null);
                    oldTbluserOfTblprofessional = em.merge(oldTbluserOfTblprofessional);
                }
                tblprofessional.setTbluser(tbluser);
                tblprofessional = em.merge(tblprofessional);
            }
            if (cityCode != null) {
                cityCode.getTbluserCollection().add(tbluser);
                cityCode = em.merge(cityCode);
            }
            for (Tblrole tblroleCollectionTblrole : tbluser.getTblroleCollection()) {
                Tbluser oldUserIDOfTblroleCollectionTblrole = tblroleCollectionTblrole.getUserID();
                tblroleCollectionTblrole.setUserID(tbluser);
                tblroleCollectionTblrole = em.merge(tblroleCollectionTblrole);
                if (oldUserIDOfTblroleCollectionTblrole != null) {
                    oldUserIDOfTblroleCollectionTblrole.getTblroleCollection().remove(tblroleCollectionTblrole);
                    oldUserIDOfTblroleCollectionTblrole = em.merge(oldUserIDOfTblroleCollectionTblrole);
                }
            }
            for (Tbltracking tbltrackingCollectionTbltracking : tbluser.getTbltrackingCollection()) {
                Tbluser oldTbluserOfTbltrackingCollectionTbltracking = tbltrackingCollectionTbltracking.getTbluser();
                tbltrackingCollectionTbltracking.setTbluser(tbluser);
                tbltrackingCollectionTbltracking = em.merge(tbltrackingCollectionTbltracking);
                if (oldTbluserOfTbltrackingCollectionTbltracking != null) {
                    oldTbluserOfTbltrackingCollectionTbltracking.getTbltrackingCollection().remove(tbltrackingCollectionTbltracking);
                    oldTbluserOfTbltrackingCollectionTbltracking = em.merge(oldTbluserOfTbltrackingCollectionTbltracking);
                }
            }
            for (Tblproject tblprojectCollectionTblproject : tbluser.getTblprojectCollection()) {
                Tbluser oldUserIDOfTblprojectCollectionTblproject = tblprojectCollectionTblproject.getUserID();
                tblprojectCollectionTblproject.setUserID(tbluser);
                tblprojectCollectionTblproject = em.merge(tblprojectCollectionTblproject);
                if (oldUserIDOfTblprojectCollectionTblproject != null) {
                    oldUserIDOfTblprojectCollectionTblproject.getTblprojectCollection().remove(tblprojectCollectionTblproject);
                    oldUserIDOfTblprojectCollectionTblproject = em.merge(oldUserIDOfTblprojectCollectionTblproject);
                }
            }
            for (Tblstory tblstoryCollectionTblstory : tbluser.getTblstoryCollection()) {
                Tbluser oldUserIDOfTblstoryCollectionTblstory = tblstoryCollectionTblstory.getUserID();
                tblstoryCollectionTblstory.setUserID(tbluser);
                tblstoryCollectionTblstory = em.merge(tblstoryCollectionTblstory);
                if (oldUserIDOfTblstoryCollectionTblstory != null) {
                    oldUserIDOfTblstoryCollectionTblstory.getTblstoryCollection().remove(tblstoryCollectionTblstory);
                    oldUserIDOfTblstoryCollectionTblstory = em.merge(oldUserIDOfTblstoryCollectionTblstory);
                }
            }
            for (Tblorder tblorderCollectionTblorder : tbluser.getTblorderCollection()) {
                Tbluser oldUserIDOfTblorderCollectionTblorder = tblorderCollectionTblorder.getUserID();
                tblorderCollectionTblorder.setUserID(tbluser);
                tblorderCollectionTblorder = em.merge(tblorderCollectionTblorder);
                if (oldUserIDOfTblorderCollectionTblorder != null) {
                    oldUserIDOfTblorderCollectionTblorder.getTblorderCollection().remove(tblorderCollectionTblorder);
                    oldUserIDOfTblorderCollectionTblorder = em.merge(oldUserIDOfTblorderCollectionTblorder);
                }
            }
            for (Tblideabook tblideabookCollectionTblideabook : tbluser.getTblideabookCollection()) {
                Tbluser oldUserIDOfTblideabookCollectionTblideabook = tblideabookCollectionTblideabook.getUserID();
                tblideabookCollectionTblideabook.setUserID(tbluser);
                tblideabookCollectionTblideabook = em.merge(tblideabookCollectionTblideabook);
                if (oldUserIDOfTblideabookCollectionTblideabook != null) {
                    oldUserIDOfTblideabookCollectionTblideabook.getTblideabookCollection().remove(tblideabookCollectionTblideabook);
                    oldUserIDOfTblideabookCollectionTblideabook = em.merge(oldUserIDOfTblideabookCollectionTblideabook);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbluser tbluser) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbluser persistentTbluser = em.find(Tbluser.class, tbluser.getUserID());
            Tblprofessional tblprofessionalOld = persistentTbluser.getTblprofessional();
            Tblprofessional tblprofessionalNew = tbluser.getTblprofessional();
            Tlcity cityCodeOld = persistentTbluser.getCityCode();
            Tlcity cityCodeNew = tbluser.getCityCode();
            Collection<Tblrole> tblroleCollectionOld = persistentTbluser.getTblroleCollection();
            Collection<Tblrole> tblroleCollectionNew = tbluser.getTblroleCollection();
            Collection<Tbltracking> tbltrackingCollectionOld = persistentTbluser.getTbltrackingCollection();
            Collection<Tbltracking> tbltrackingCollectionNew = tbluser.getTbltrackingCollection();
            Collection<Tblproject> tblprojectCollectionOld = persistentTbluser.getTblprojectCollection();
            Collection<Tblproject> tblprojectCollectionNew = tbluser.getTblprojectCollection();
            Collection<Tblstory> tblstoryCollectionOld = persistentTbluser.getTblstoryCollection();
            Collection<Tblstory> tblstoryCollectionNew = tbluser.getTblstoryCollection();
            Collection<Tblorder> tblorderCollectionOld = persistentTbluser.getTblorderCollection();
            Collection<Tblorder> tblorderCollectionNew = tbluser.getTblorderCollection();
            Collection<Tblideabook> tblideabookCollectionOld = persistentTbluser.getTblideabookCollection();
            Collection<Tblideabook> tblideabookCollectionNew = tbluser.getTblideabookCollection();
            List<String> illegalOrphanMessages = null;
            if (tblprofessionalOld != null && !tblprofessionalOld.equals(tblprofessionalNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Tblprofessional " + tblprofessionalOld + " since its tbluser field is not nullable.");
            }
            for (Tblrole tblroleCollectionOldTblrole : tblroleCollectionOld) {
                if (!tblroleCollectionNew.contains(tblroleCollectionOldTblrole)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblrole " + tblroleCollectionOldTblrole + " since its userID field is not nullable.");
                }
            }
            for (Tbltracking tbltrackingCollectionOldTbltracking : tbltrackingCollectionOld) {
                if (!tbltrackingCollectionNew.contains(tbltrackingCollectionOldTbltracking)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbltracking " + tbltrackingCollectionOldTbltracking + " since its tbluser field is not nullable.");
                }
            }
            for (Tblproject tblprojectCollectionOldTblproject : tblprojectCollectionOld) {
                if (!tblprojectCollectionNew.contains(tblprojectCollectionOldTblproject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblproject " + tblprojectCollectionOldTblproject + " since its userID field is not nullable.");
                }
            }
            for (Tblstory tblstoryCollectionOldTblstory : tblstoryCollectionOld) {
                if (!tblstoryCollectionNew.contains(tblstoryCollectionOldTblstory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblstory " + tblstoryCollectionOldTblstory + " since its userID field is not nullable.");
                }
            }
            for (Tblorder tblorderCollectionOldTblorder : tblorderCollectionOld) {
                if (!tblorderCollectionNew.contains(tblorderCollectionOldTblorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblorder " + tblorderCollectionOldTblorder + " since its userID field is not nullable.");
                }
            }
            for (Tblideabook tblideabookCollectionOldTblideabook : tblideabookCollectionOld) {
                if (!tblideabookCollectionNew.contains(tblideabookCollectionOldTblideabook)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblideabook " + tblideabookCollectionOldTblideabook + " since its userID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tblprofessionalNew != null) {
                tblprofessionalNew = em.getReference(tblprofessionalNew.getClass(), tblprofessionalNew.getUserID());
                tbluser.setTblprofessional(tblprofessionalNew);
            }
            if (cityCodeNew != null) {
                cityCodeNew = em.getReference(cityCodeNew.getClass(), cityCodeNew.getCityCode());
                tbluser.setCityCode(cityCodeNew);
            }
            Collection<Tblrole> attachedTblroleCollectionNew = new ArrayList<Tblrole>();
            for (Tblrole tblroleCollectionNewTblroleToAttach : tblroleCollectionNew) {
                tblroleCollectionNewTblroleToAttach = em.getReference(tblroleCollectionNewTblroleToAttach.getClass(), tblroleCollectionNewTblroleToAttach.getRoleID());
                attachedTblroleCollectionNew.add(tblroleCollectionNewTblroleToAttach);
            }
            tblroleCollectionNew = attachedTblroleCollectionNew;
            tbluser.setTblroleCollection(tblroleCollectionNew);
            Collection<Tbltracking> attachedTbltrackingCollectionNew = new ArrayList<Tbltracking>();
            for (Tbltracking tbltrackingCollectionNewTbltrackingToAttach : tbltrackingCollectionNew) {
                tbltrackingCollectionNewTbltrackingToAttach = em.getReference(tbltrackingCollectionNewTbltrackingToAttach.getClass(), tbltrackingCollectionNewTbltrackingToAttach.getTbltrackingPK());
                attachedTbltrackingCollectionNew.add(tbltrackingCollectionNewTbltrackingToAttach);
            }
            tbltrackingCollectionNew = attachedTbltrackingCollectionNew;
            tbluser.setTbltrackingCollection(tbltrackingCollectionNew);
            Collection<Tblproject> attachedTblprojectCollectionNew = new ArrayList<Tblproject>();
            for (Tblproject tblprojectCollectionNewTblprojectToAttach : tblprojectCollectionNew) {
                tblprojectCollectionNewTblprojectToAttach = em.getReference(tblprojectCollectionNewTblprojectToAttach.getClass(), tblprojectCollectionNewTblprojectToAttach.getProjectID());
                attachedTblprojectCollectionNew.add(tblprojectCollectionNewTblprojectToAttach);
            }
            tblprojectCollectionNew = attachedTblprojectCollectionNew;
            tbluser.setTblprojectCollection(tblprojectCollectionNew);
            Collection<Tblstory> attachedTblstoryCollectionNew = new ArrayList<Tblstory>();
            for (Tblstory tblstoryCollectionNewTblstoryToAttach : tblstoryCollectionNew) {
                tblstoryCollectionNewTblstoryToAttach = em.getReference(tblstoryCollectionNewTblstoryToAttach.getClass(), tblstoryCollectionNewTblstoryToAttach.getStoryID());
                attachedTblstoryCollectionNew.add(tblstoryCollectionNewTblstoryToAttach);
            }
            tblstoryCollectionNew = attachedTblstoryCollectionNew;
            tbluser.setTblstoryCollection(tblstoryCollectionNew);
            Collection<Tblorder> attachedTblorderCollectionNew = new ArrayList<Tblorder>();
            for (Tblorder tblorderCollectionNewTblorderToAttach : tblorderCollectionNew) {
                tblorderCollectionNewTblorderToAttach = em.getReference(tblorderCollectionNewTblorderToAttach.getClass(), tblorderCollectionNewTblorderToAttach.getOrderID());
                attachedTblorderCollectionNew.add(tblorderCollectionNewTblorderToAttach);
            }
            tblorderCollectionNew = attachedTblorderCollectionNew;
            tbluser.setTblorderCollection(tblorderCollectionNew);
            Collection<Tblideabook> attachedTblideabookCollectionNew = new ArrayList<Tblideabook>();
            for (Tblideabook tblideabookCollectionNewTblideabookToAttach : tblideabookCollectionNew) {
                tblideabookCollectionNewTblideabookToAttach = em.getReference(tblideabookCollectionNewTblideabookToAttach.getClass(), tblideabookCollectionNewTblideabookToAttach.getIdeaBookID());
                attachedTblideabookCollectionNew.add(tblideabookCollectionNewTblideabookToAttach);
            }
            tblideabookCollectionNew = attachedTblideabookCollectionNew;
            tbluser.setTblideabookCollection(tblideabookCollectionNew);
            tbluser = em.merge(tbluser);
            if (tblprofessionalNew != null && !tblprofessionalNew.equals(tblprofessionalOld)) {
                Tbluser oldTbluserOfTblprofessional = tblprofessionalNew.getTbluser();
                if (oldTbluserOfTblprofessional != null) {
                    oldTbluserOfTblprofessional.setTblprofessional(null);
                    oldTbluserOfTblprofessional = em.merge(oldTbluserOfTblprofessional);
                }
                tblprofessionalNew.setTbluser(tbluser);
                tblprofessionalNew = em.merge(tblprofessionalNew);
            }
            if (cityCodeOld != null && !cityCodeOld.equals(cityCodeNew)) {
                cityCodeOld.getTbluserCollection().remove(tbluser);
                cityCodeOld = em.merge(cityCodeOld);
            }
            if (cityCodeNew != null && !cityCodeNew.equals(cityCodeOld)) {
                cityCodeNew.getTbluserCollection().add(tbluser);
                cityCodeNew = em.merge(cityCodeNew);
            }
            for (Tblrole tblroleCollectionNewTblrole : tblroleCollectionNew) {
                if (!tblroleCollectionOld.contains(tblroleCollectionNewTblrole)) {
                    Tbluser oldUserIDOfTblroleCollectionNewTblrole = tblroleCollectionNewTblrole.getUserID();
                    tblroleCollectionNewTblrole.setUserID(tbluser);
                    tblroleCollectionNewTblrole = em.merge(tblroleCollectionNewTblrole);
                    if (oldUserIDOfTblroleCollectionNewTblrole != null && !oldUserIDOfTblroleCollectionNewTblrole.equals(tbluser)) {
                        oldUserIDOfTblroleCollectionNewTblrole.getTblroleCollection().remove(tblroleCollectionNewTblrole);
                        oldUserIDOfTblroleCollectionNewTblrole = em.merge(oldUserIDOfTblroleCollectionNewTblrole);
                    }
                }
            }
            for (Tbltracking tbltrackingCollectionNewTbltracking : tbltrackingCollectionNew) {
                if (!tbltrackingCollectionOld.contains(tbltrackingCollectionNewTbltracking)) {
                    Tbluser oldTbluserOfTbltrackingCollectionNewTbltracking = tbltrackingCollectionNewTbltracking.getTbluser();
                    tbltrackingCollectionNewTbltracking.setTbluser(tbluser);
                    tbltrackingCollectionNewTbltracking = em.merge(tbltrackingCollectionNewTbltracking);
                    if (oldTbluserOfTbltrackingCollectionNewTbltracking != null && !oldTbluserOfTbltrackingCollectionNewTbltracking.equals(tbluser)) {
                        oldTbluserOfTbltrackingCollectionNewTbltracking.getTbltrackingCollection().remove(tbltrackingCollectionNewTbltracking);
                        oldTbluserOfTbltrackingCollectionNewTbltracking = em.merge(oldTbluserOfTbltrackingCollectionNewTbltracking);
                    }
                }
            }
            for (Tblproject tblprojectCollectionNewTblproject : tblprojectCollectionNew) {
                if (!tblprojectCollectionOld.contains(tblprojectCollectionNewTblproject)) {
                    Tbluser oldUserIDOfTblprojectCollectionNewTblproject = tblprojectCollectionNewTblproject.getUserID();
                    tblprojectCollectionNewTblproject.setUserID(tbluser);
                    tblprojectCollectionNewTblproject = em.merge(tblprojectCollectionNewTblproject);
                    if (oldUserIDOfTblprojectCollectionNewTblproject != null && !oldUserIDOfTblprojectCollectionNewTblproject.equals(tbluser)) {
                        oldUserIDOfTblprojectCollectionNewTblproject.getTblprojectCollection().remove(tblprojectCollectionNewTblproject);
                        oldUserIDOfTblprojectCollectionNewTblproject = em.merge(oldUserIDOfTblprojectCollectionNewTblproject);
                    }
                }
            }
            for (Tblstory tblstoryCollectionNewTblstory : tblstoryCollectionNew) {
                if (!tblstoryCollectionOld.contains(tblstoryCollectionNewTblstory)) {
                    Tbluser oldUserIDOfTblstoryCollectionNewTblstory = tblstoryCollectionNewTblstory.getUserID();
                    tblstoryCollectionNewTblstory.setUserID(tbluser);
                    tblstoryCollectionNewTblstory = em.merge(tblstoryCollectionNewTblstory);
                    if (oldUserIDOfTblstoryCollectionNewTblstory != null && !oldUserIDOfTblstoryCollectionNewTblstory.equals(tbluser)) {
                        oldUserIDOfTblstoryCollectionNewTblstory.getTblstoryCollection().remove(tblstoryCollectionNewTblstory);
                        oldUserIDOfTblstoryCollectionNewTblstory = em.merge(oldUserIDOfTblstoryCollectionNewTblstory);
                    }
                }
            }
            for (Tblorder tblorderCollectionNewTblorder : tblorderCollectionNew) {
                if (!tblorderCollectionOld.contains(tblorderCollectionNewTblorder)) {
                    Tbluser oldUserIDOfTblorderCollectionNewTblorder = tblorderCollectionNewTblorder.getUserID();
                    tblorderCollectionNewTblorder.setUserID(tbluser);
                    tblorderCollectionNewTblorder = em.merge(tblorderCollectionNewTblorder);
                    if (oldUserIDOfTblorderCollectionNewTblorder != null && !oldUserIDOfTblorderCollectionNewTblorder.equals(tbluser)) {
                        oldUserIDOfTblorderCollectionNewTblorder.getTblorderCollection().remove(tblorderCollectionNewTblorder);
                        oldUserIDOfTblorderCollectionNewTblorder = em.merge(oldUserIDOfTblorderCollectionNewTblorder);
                    }
                }
            }
            for (Tblideabook tblideabookCollectionNewTblideabook : tblideabookCollectionNew) {
                if (!tblideabookCollectionOld.contains(tblideabookCollectionNewTblideabook)) {
                    Tbluser oldUserIDOfTblideabookCollectionNewTblideabook = tblideabookCollectionNewTblideabook.getUserID();
                    tblideabookCollectionNewTblideabook.setUserID(tbluser);
                    tblideabookCollectionNewTblideabook = em.merge(tblideabookCollectionNewTblideabook);
                    if (oldUserIDOfTblideabookCollectionNewTblideabook != null && !oldUserIDOfTblideabookCollectionNewTblideabook.equals(tbluser)) {
                        oldUserIDOfTblideabookCollectionNewTblideabook.getTblideabookCollection().remove(tblideabookCollectionNewTblideabook);
                        oldUserIDOfTblideabookCollectionNewTblideabook = em.merge(oldUserIDOfTblideabookCollectionNewTblideabook);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbluser.getUserID();
                if (findTbluser(id) == null) {
                    throw new NonexistentEntityException("The tbluser with id " + id + " no longer exists.");
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
            Tbluser tbluser;
            try {
                tbluser = em.getReference(Tbluser.class, id);
                tbluser.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbluser with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Tblprofessional tblprofessionalOrphanCheck = tbluser.getTblprofessional();
            if (tblprofessionalOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tblprofessional " + tblprofessionalOrphanCheck + " in its tblprofessional field has a non-nullable tbluser field.");
            }
            Collection<Tblrole> tblroleCollectionOrphanCheck = tbluser.getTblroleCollection();
            for (Tblrole tblroleCollectionOrphanCheckTblrole : tblroleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tblrole " + tblroleCollectionOrphanCheckTblrole + " in its tblroleCollection field has a non-nullable userID field.");
            }
            Collection<Tbltracking> tbltrackingCollectionOrphanCheck = tbluser.getTbltrackingCollection();
            for (Tbltracking tbltrackingCollectionOrphanCheckTbltracking : tbltrackingCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tbltracking " + tbltrackingCollectionOrphanCheckTbltracking + " in its tbltrackingCollection field has a non-nullable tbluser field.");
            }
            Collection<Tblproject> tblprojectCollectionOrphanCheck = tbluser.getTblprojectCollection();
            for (Tblproject tblprojectCollectionOrphanCheckTblproject : tblprojectCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tblproject " + tblprojectCollectionOrphanCheckTblproject + " in its tblprojectCollection field has a non-nullable userID field.");
            }
            Collection<Tblstory> tblstoryCollectionOrphanCheck = tbluser.getTblstoryCollection();
            for (Tblstory tblstoryCollectionOrphanCheckTblstory : tblstoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tblstory " + tblstoryCollectionOrphanCheckTblstory + " in its tblstoryCollection field has a non-nullable userID field.");
            }
            Collection<Tblorder> tblorderCollectionOrphanCheck = tbluser.getTblorderCollection();
            for (Tblorder tblorderCollectionOrphanCheckTblorder : tblorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tblorder " + tblorderCollectionOrphanCheckTblorder + " in its tblorderCollection field has a non-nullable userID field.");
            }
            Collection<Tblideabook> tblideabookCollectionOrphanCheck = tbluser.getTblideabookCollection();
            for (Tblideabook tblideabookCollectionOrphanCheckTblideabook : tblideabookCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbluser (" + tbluser + ") cannot be destroyed since the Tblideabook " + tblideabookCollectionOrphanCheckTblideabook + " in its tblideabookCollection field has a non-nullable userID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tlcity cityCode = tbluser.getCityCode();
            if (cityCode != null) {
                cityCode.getTbluserCollection().remove(tbluser);
                cityCode = em.merge(cityCode);
            }
            em.remove(tbluser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbluser> findTbluserEntities() {
        return findTbluserEntities(true, -1, -1);
    }

    public List<Tbluser> findTbluserEntities(int maxResults, int firstResult) {
        return findTbluserEntities(false, maxResults, firstResult);
    }

    private List<Tbluser> findTbluserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbluser.class));
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

    public Tbluser findTbluser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbluser.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbluserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbluser> rt = cq.from(Tbluser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Tbluser> loadAccountsByStatus(int status) {
        List<Tbluser> list = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Tbluser.findByStatus");
            query.setParameter("status", status);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

    public List<Tbluser> loadAccountsByStatusAndRole(int status, int role) {
        List<Tbluser> list = new ArrayList<>();
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Tbluser.findByStatusAndRole");
            query.setParameter("roleID", role);
            query.setParameter("status", status);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;
    }

//    public boolean setStatusAccount1(Tbluser account) {
//        boolean flag = false;
//        EntityManager em = getEntityManager();
//        try {
//            edit(account);
//            flag = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
}
