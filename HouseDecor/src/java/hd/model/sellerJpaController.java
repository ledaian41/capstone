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
import hd.entity.product;
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
public class sellerJpaController implements Serializable {

    public sellerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(seller seller) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (seller.getProductList() == null) {
            seller.setProductList(new ArrayList<product>());
        }
        List<String> illegalOrphanMessages = null;
        professional professionalOrphanCheck = seller.getProfessional();
        if (professionalOrphanCheck != null) {
            seller oldSellerOfProfessional = professionalOrphanCheck.getSeller();
            if (oldSellerOfProfessional != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The professional " + professionalOrphanCheck + " already has an item of type seller whose professional column cannot be null. Please make another selection for the professional field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            professional professional = seller.getProfessional();
            if (professional != null) {
                professional = em.getReference(professional.getClass(), professional.getUserID());
                seller.setProfessional(professional);
            }
            List<product> attachedProductList = new ArrayList<product>();
            for (product productListproductToAttach : seller.getProductList()) {
                productListproductToAttach = em.getReference(productListproductToAttach.getClass(), productListproductToAttach.getProductID());
                attachedProductList.add(productListproductToAttach);
            }
            seller.setProductList(attachedProductList);
            em.persist(seller);
            if (professional != null) {
                professional.setSeller(seller);
                professional = em.merge(professional);
            }
            for (product productListproduct : seller.getProductList()) {
                seller oldTblSelleruserIDOfProductListproduct = productListproduct.getTblSelleruserID();
                productListproduct.setTblSelleruserID(seller);
                productListproduct = em.merge(productListproduct);
                if (oldTblSelleruserIDOfProductListproduct != null) {
                    oldTblSelleruserIDOfProductListproduct.getProductList().remove(productListproduct);
                    oldTblSelleruserIDOfProductListproduct = em.merge(oldTblSelleruserIDOfProductListproduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findseller(seller.getUserID()) != null) {
                throw new PreexistingEntityException("seller " + seller + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(seller seller) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            seller persistentseller = em.find(seller.class, seller.getUserID());
            professional professionalOld = persistentseller.getProfessional();
            professional professionalNew = seller.getProfessional();
            List<product> productListOld = persistentseller.getProductList();
            List<product> productListNew = seller.getProductList();
            List<String> illegalOrphanMessages = null;
            if (professionalNew != null && !professionalNew.equals(professionalOld)) {
                seller oldSellerOfProfessional = professionalNew.getSeller();
                if (oldSellerOfProfessional != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The professional " + professionalNew + " already has an item of type seller whose professional column cannot be null. Please make another selection for the professional field.");
                }
            }
            for (product productListOldproduct : productListOld) {
                if (!productListNew.contains(productListOldproduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain product " + productListOldproduct + " since its tblSelleruserID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (professionalNew != null) {
                professionalNew = em.getReference(professionalNew.getClass(), professionalNew.getUserID());
                seller.setProfessional(professionalNew);
            }
            List<product> attachedProductListNew = new ArrayList<product>();
            for (product productListNewproductToAttach : productListNew) {
                productListNewproductToAttach = em.getReference(productListNewproductToAttach.getClass(), productListNewproductToAttach.getProductID());
                attachedProductListNew.add(productListNewproductToAttach);
            }
            productListNew = attachedProductListNew;
            seller.setProductList(productListNew);
            seller = em.merge(seller);
            if (professionalOld != null && !professionalOld.equals(professionalNew)) {
                professionalOld.setSeller(null);
                professionalOld = em.merge(professionalOld);
            }
            if (professionalNew != null && !professionalNew.equals(professionalOld)) {
                professionalNew.setSeller(seller);
                professionalNew = em.merge(professionalNew);
            }
            for (product productListNewproduct : productListNew) {
                if (!productListOld.contains(productListNewproduct)) {
                    seller oldTblSelleruserIDOfProductListNewproduct = productListNewproduct.getTblSelleruserID();
                    productListNewproduct.setTblSelleruserID(seller);
                    productListNewproduct = em.merge(productListNewproduct);
                    if (oldTblSelleruserIDOfProductListNewproduct != null && !oldTblSelleruserIDOfProductListNewproduct.equals(seller)) {
                        oldTblSelleruserIDOfProductListNewproduct.getProductList().remove(productListNewproduct);
                        oldTblSelleruserIDOfProductListNewproduct = em.merge(oldTblSelleruserIDOfProductListNewproduct);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = seller.getUserID();
                if (findseller(id) == null) {
                    throw new NonexistentEntityException("The seller with id " + id + " no longer exists.");
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
            seller seller;
            try {
                seller = em.getReference(seller.class, id);
                seller.getUserID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The seller with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<product> productListOrphanCheck = seller.getProductList();
            for (product productListOrphanCheckproduct : productListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This seller (" + seller + ") cannot be destroyed since the product " + productListOrphanCheckproduct + " in its productList field has a non-nullable tblSelleruserID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            professional professional = seller.getProfessional();
            if (professional != null) {
                professional.setSeller(null);
                professional = em.merge(professional);
            }
            em.remove(seller);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<seller> findsellerEntities() {
        return findsellerEntities(true, -1, -1);
    }

    public List<seller> findsellerEntities(int maxResults, int firstResult) {
        return findsellerEntities(false, maxResults, firstResult);
    }

    private List<seller> findsellerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(seller.class));
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

    public seller findseller(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(seller.class, id);
        } finally {
            em.close();
        }
    }

    public int getsellerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<seller> rt = cq.from(seller.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
