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
import hd.entity.Tblprofessional;
import hd.entity.Tblproduct;
import hd.entity.Tblseller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lê Đại An
 */
public class TblsellerJpaController implements Serializable {

    public TblsellerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tblseller tblseller) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (tblseller.getTblproductCollection() == null) {
            tblseller.setTblproductCollection(new ArrayList<Tblproduct>());
        }
        List<String> illegalOrphanMessages = null;
        Tblprofessional tblprofessionalOrphanCheck = tblseller.getTblprofessional();
        if (tblprofessionalOrphanCheck != null) {
            Tblseller oldTblsellerOfTblprofessional = tblprofessionalOrphanCheck.getTblseller();
            if (oldTblsellerOfTblprofessional != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Tblprofessional " + tblprofessionalOrphanCheck + " already has an item of type Tblseller whose tblprofessional column cannot be null. Please make another selection for the tblprofessional field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblprofessional tblprofessional = tblseller.getTblprofessional();
            if (tblprofessional != null) {
                tblprofessional = em.getReference(tblprofessional.getClass(), tblprofessional.getUserID());
                tblseller.setTblprofessional(tblprofessional);
            }
            Collection<Tblproduct> attachedTblproductCollection = new ArrayList<Tblproduct>();
            for (Tblproduct tblproductCollectionTblproductToAttach : tblseller.getTblproductCollection()) {
                tblproductCollectionTblproductToAttach = em.getReference(tblproductCollectionTblproductToAttach.getClass(), tblproductCollectionTblproductToAttach.getProductID());
                attachedTblproductCollection.add(tblproductCollectionTblproductToAttach);
            }
            tblseller.setTblproductCollection(attachedTblproductCollection);
            em.persist(tblseller);
            if (tblprofessional != null) {
                tblprofessional.setTblseller(tblseller);
                tblprofessional = em.merge(tblprofessional);
            }
            for (Tblproduct tblproductCollectionTblproduct : tblseller.getTblproductCollection()) {
                Tblseller oldTblSelleruserIDOfTblproductCollectionTblproduct = tblproductCollectionTblproduct.getTblSelleruserID();
                tblproductCollectionTblproduct.setTblSelleruserID(tblseller);
                tblproductCollectionTblproduct = em.merge(tblproductCollectionTblproduct);
                if (oldTblSelleruserIDOfTblproductCollectionTblproduct != null) {
                    oldTblSelleruserIDOfTblproductCollectionTblproduct.getTblproductCollection().remove(tblproductCollectionTblproduct);
                    oldTblSelleruserIDOfTblproductCollectionTblproduct = em.merge(oldTblSelleruserIDOfTblproductCollectionTblproduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTblseller(tblseller.getUserID()) != null) {
                throw new PreexistingEntityException("Tblseller " + tblseller + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tblseller tblseller) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tblseller persistentTblseller = em.find(Tblseller.class, tblseller.getUserID());
            Tblprofessional tblprofessionalOld = persistentTblseller.getTblprofessional();
            Tblprofessional tblprofessionalNew = tblseller.getTblprofessional();
            Collection<Tblproduct> tblproductCollectionOld = persistentTblseller.getTblproductCollection();
            Collection<Tblproduct> tblproductCollectionNew = tblseller.getTblproductCollection();
            List<String> illegalOrphanMessages = null;
            if (tblprofessionalNew != null && !tblprofessionalNew.equals(tblprofessionalOld)) {
                Tblseller oldTblsellerOfTblprofessional = tblprofessionalNew.getTblseller();
                if (oldTblsellerOfTblprofessional != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Tblprofessional " + tblprofessionalNew + " already has an item of type Tblseller whose tblprofessional column cannot be null. Please make another selection for the tblprofessional field.");
                }
            }
            for (Tblproduct tblproductCollectionOldTblproduct : tblproductCollectionOld) {
                if (!tblproductCollectionNew.contains(tblproductCollectionOldTblproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tblproduct " + tblproductCollectionOldTblproduct + " since its tblSelleruserID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tblprofessionalNew != null) {
                tblprofessionalNew = em.getReference(tblprofessionalNew.getClass(), tblprofessionalNew.getUserID());
                tblseller.setTblprofessional(tblprofessionalNew);
            }
            Collection<Tblproduct> attachedTblproductCollectionNew = new ArrayList<Tblproduct>();
            for (Tblproduct tblproductCollectionNewTblproductToAttach : tblproductCollectionNew) {
                tblproductCollectionNewTblproductToAttach = em.getReference(tblproductCollectionNewTblproductToAttach.getClass(), tblproductCollectionNewTblproductToAttach.getProductID());
                attachedTblproductCollectionNew.add(tblproductCollectionNewTblproductToAttach);
            }
            tblproductCollectionNew = attachedTblproductCollectionNew;
            tblseller.setTblproductCollection(tblproductCollectionNew);
            tblseller = em.merge(tblseller);
            if (tblprofessionalOld != null && !tblprofessionalOld.equals(tblprofessionalNew)) {
                tblprofessionalOld.setTblseller(null);
                tblprofessionalOld = em.merge(tblprofessionalOld);
            }
            if (tblprofessionalNew != null && !tblprofessionalNew.equals(tblprofessionalOld)) {
                tblprofessionalNew.setTblseller(tblseller);
                tblprofessionalNew = em.merge(tblprofessionalNew);
            }
            for (Tblproduct tblproductCollectionNewTblproduct : tblproductCollectionNew) {
                if (!tblproductCollectionOld.contains(tblproductCollectionNewTblproduct)) {
                    Tblseller oldTblSelleruserIDOfTblproductCollectionNewTblproduct = tblproductCollectionNewTblproduct.getTblSelleruserID();
                    tblproductCollectionNewTblproduct.setTblSelleruserID(tblseller);
                    tblproductCollectionNewTblproduct = em.merge(tblproductCollectionNewTblproduct);
                    if (oldTblSelleruserIDOfTblproductCollectionNewTblproduct != null && !oldTblSelleruserIDOfTblproductCollectionNewTblproduct.equals(tblseller)) {
                        oldTblSelleruserIDOfTblproductCollectionNewTblproduct.getTblproductCollection().remove(tblproductCollectionNewTblproduct);
                        oldTblSelleruserIDOfTblproductCollectionNewTblproduct = em.merge(oldTblSelleruserIDOfTblproductCollectionNewTblproduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblseller.getUserID();
                if (findTblseller(id) == null) {
                    throw new NonexistentEntityException("The tblseller with id " + id + " no longer exists.");
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
            Tblseller tblseller;
            try {
                tblseller = em.getReference(Tblseller.class, id);
                tblseller.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblseller with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tblproduct> tblproductCollectionOrphanCheck = tblseller.getTblproductCollection();
            for (Tblproduct tblproductCollectionOrphanCheckTblproduct : tblproductCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tblseller (" + tblseller + ") cannot be destroyed since the Tblproduct " + tblproductCollectionOrphanCheckTblproduct + " in its tblproductCollection field has a non-nullable tblSelleruserID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tblprofessional tblprofessional = tblseller.getTblprofessional();
            if (tblprofessional != null) {
                tblprofessional.setTblseller(null);
                tblprofessional = em.merge(tblprofessional);
            }
            em.remove(tblseller);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tblseller> findTblsellerEntities() {
        return findTblsellerEntities(true, -1, -1);
    }

    public List<Tblseller> findTblsellerEntities(int maxResults, int firstResult) {
        return findTblsellerEntities(false, maxResults, firstResult);
    }

    private List<Tblseller> findTblsellerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tblseller.class));
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

    public Tblseller findTblseller(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tblseller.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblsellerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tblseller> rt = cq.from(Tblseller.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
