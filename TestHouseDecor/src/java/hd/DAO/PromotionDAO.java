/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.DAO;

import hd.entity.Promotion;
import hd.entity.SellerInfo;
import hd.service.HDSystem;
import java.io.Serializable;
import java.sql.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author cuk3t
 */
public class PromotionDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestHouseDecorPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Promotion> showPromotionBySeler(int sellerID) {

        EntityManager em = emf.createEntityManager();

        em.getEntityManagerFactory().getCache().evictAll();
        try {
            String jpql = "SELECT p FROM Promotion p WHERE p.sellerInfoUserId.userId =" + sellerID + "";
            Query query = em.createQuery(jpql);
            return (List<Promotion>) query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void deletePromotion(int promotionID, int sellerID) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT p FROM Promotion p WHERE p.sellerInfoUserId.userId =" + sellerID + " and p.id =" + promotionID;
        Query query = em.createQuery(jpql);
        Promotion entity = (Promotion) query.getSingleResult();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public void inserPormotion(String promotionName, Date sDate, Date eDate, String description, int sellerID) {
        EntityManager em = emf.createEntityManager();
        SellerInfo seller = em.find(SellerInfo.class, sellerID);
        Promotion entity = new Promotion();
        entity.setName(promotionName);
        entity.setStartDate(sDate);
        entity.setEndDate(eDate);
        entity.setDescription(description);
        entity.setSellerInfoUserId(seller);
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        //Send promotion
        HDSystem system = new HDSystem();
        new Thread(new Runnable() {
            @Override
            public void run() {
                system.sendPromotionEmail(entity);
            }
        }).start();
        //End send promotion
    }
}
