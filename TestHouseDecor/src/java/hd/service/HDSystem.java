/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.service;

import hd.JPA.CategoryJpaController;
import hd.JPA.OrderDetailJpaController;
import hd.JPA.PromotionDetailJpaController;
import hd.JPA.TrackingJpaController;
import hd.JPA.UserJpaController;
import hd.entity.Category;
import hd.entity.OrderDetail;
import hd.entity.Orders;
import hd.entity.Product;
import hd.entity.Promotion;
import hd.entity.SellerInfo;
import hd.entity.Tracking;
import hd.entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author An
 */
public class HDSystem {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestHouseDecorPU");

    public void addTracking(int userId, int categoryId) {
        try {
            boolean isExist = false;
            TrackingJpaController trackingJpa = new TrackingJpaController(emf);
            UserJpaController userJpa = new UserJpaController(emf);
            CategoryJpaController cateJpa = new CategoryJpaController(emf);
            User user = userJpa.findUser(userId);
            Category cate = cateJpa.findCategory(categoryId);
            List<Tracking> list = trackingJpa.findTrackingEntities();
            Tracking tracking = new Tracking();
            tracking.setUser(user);
            tracking.setCategory(cate);
            tracking.setCount(0);
            Date lastUpdate = new Date();
            for (Tracking dto : list) {
                if (isExistTracking(dto, userId, categoryId)) {
                    tracking = dto;
                    isExist = true;
                    break;
                }
            }
            tracking.setLastUpdate(lastUpdate);
            tracking.setCount(tracking.getCount() + 1);
            if (isExist) {
                trackingJpa.edit(tracking);
            } else {
                trackingJpa.create(tracking);
            }
            removeTracking(user.getUserId(), 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendPromotionEmail(Promotion promotion) {
        PromotionDetailJpaController proDetailJpa = new PromotionDetailJpaController(emf);
        List<Product> products = proDetailJpa.getProduct(promotion.getId());
        List<User> users = new ArrayList<>();
        for (Product product : products) {
            List<User> tmpUsers = getTargetSendPromotion(product);
            for (User user : tmpUsers) {
                if (!users.contains(user)) {
                    users.add(user);
                }
            }
        }
        for (User user : users) {
            sendPromotion(user, promotion);
        }
    }

    public void sendOrderEmail(Orders order) {
        OrderDetailJpaController orderDetailJpa = new OrderDetailJpaController(emf);
        List<OrderDetail> orderDetails = orderDetailJpa.findOrderDetailEntities();
        List<SellerInfo> sellers = new ArrayList<>();
        for (OrderDetail detail : orderDetails) {
            SellerInfo seller = detail.getProductId().getUserId();
            if (!sellers.contains(seller)) {
                sellers.add(seller);
            }
        }
        for (SellerInfo seller : sellers) {
            HashMap<Product, Integer> products = new HashMap<>();
            for (OrderDetail detail : orderDetails) {
                if (detail.getProductId().getUserId().equals(seller)) {
                    products.put(detail.getProductId(), detail.getQuantity());
                }
            }
            //Send email to seller
            sendOrder(seller, order, products);
        }
    }

    private void sendOrder(SellerInfo seller, Orders order, HashMap<Product, Integer> products) {
        String to = seller.getProfessional().getUser().getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HDSystemConfig.host);
        props.put("mail.smtp.port", HDSystemConfig.port);
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HDSystemConfig.username, HDSystemConfig.password);
            }
        });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(HDSystemConfig.from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            User customer = order.getUserId();
            String customerName = customer.getLastname() + " " + customer.getFirstname();
            message.setSubject("New Order from House Decor: " + customerName);
            String header = "Dear " + seller.getSellerName() + ",\r\n";
            String body = "Order Information:\r\n"
                    + "Order ID: " + order.getOrderId() + "\r\n"
                    + "Customer: " + customerName + " - " + customer.getPhoneNumber() + "\r\n"
                    + "Customer's email: " + customer.getEmail() + "\r\n"
                    + "Time: " + order.getCreatedTime() + "\r\n"
                    + "Products:" + "\r\n";
            for (HashMap.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                body = body + "  - Product ID: " + product.getProductId() + "\r\n"
                        + "    Name: " + product.getProductName() + "\r\n"
                        + "    Quantity: " + quantity + "\r\n";
            }
            String footer = "\r\n\r\nRegards,\r\nHouse Decor";
            // Now set the actual message
            message.setText(header + body + footer);
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendPromotion(User user, Promotion promotion) {
        String to = user.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HDSystemConfig.host);
        props.put("mail.smtp.port", HDSystemConfig.port);
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(HDSystemConfig.username, HDSystemConfig.password);
            }
        });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(HDSystemConfig.from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject("Promotion " + promotion.getName());
            String header = "Hi " + user.getLastname() + " " + user.getFirstname() + "\r\n";
            String body = "Sales today:\r\n"
                    + "Time: "
                    + promotion.getStartDateString() + " to " + promotion.getEndDateString()
                    + "\r\n";
            String footer = "Regards,\r\nHouse Decor";
            // Now set the actual message
            message.setText(header + body + footer);
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> getTargetSendPromotion(Product product) {
        try {
            List<User> users = new ArrayList<>();
            TrackingJpaController trackingJpa = new TrackingJpaController(emf);
            int categoryId = product.getCategoryCategoryId().getCategoryId();
            List<Tracking> trackings = trackingJpa.findTrackingByCategoryId(categoryId);
            for (Tracking dto : trackings) {
                if (dto.getCount() > 3) {
                    users.add(dto.getUser());
                }
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setNotificationIdeaBook(HttpServletRequest request) {
        try {
            int msg = 0;
            if (request.getServletContext().getAttribute("IDEABOOK") == null) {
                msg = 0;
            } else {
                msg = (int) request.getServletContext().getAttribute("IDEABOOK");
            }
            msg = msg + 1;
            request.getServletContext().setAttribute("IDEABOOK", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNotificationProject(HttpServletRequest request) {
        try {
            int msg = 0;
            if (request.getServletContext().getAttribute("PROJECT") == null) {
                msg = 0;
            } else {
                msg = (int) request.getServletContext().getAttribute("PROJECT");
            }
            msg = msg + 1;
            request.getServletContext().setAttribute("PROJECT", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNotificationProduct(HttpServletRequest request) {
        try {
            int msg = 0;
            if (request.getServletContext().getAttribute("PRODUCT") == null) {
                msg = 0;
            } else {
                msg = (int) request.getServletContext().getAttribute("PRODUCT");
            }
            msg = msg + 1;
            request.getServletContext().setAttribute("PRODUCT", msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isExistTracking(Tracking dto, int userId, int categoryId) {
        return dto.getUser().getUserId() == userId
                && dto.getCategory().getCategoryId() == categoryId;
    }

    private void removeTracking(int userId, int outdateDays) {
        TrackingJpaController trackingJpa = new TrackingJpaController(emf);
        try {
            trackingJpa.removeOutdateTracking(outdateDays, new Date(), userId);
        } catch (Exception ex) {
            Logger.getLogger(HDSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
}
