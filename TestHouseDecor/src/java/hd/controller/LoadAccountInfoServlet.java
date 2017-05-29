/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.controller;

import hd.DTO.AccInfoDTO;
import hd.DTO.ProjectIdNameDTO;
import hd.JPA.TblprojectJpaController;
import hd.entity.Tblproject;
import hd.entity.Tbluser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lê Đại An
 */
public class LoadAccountInfoServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestHouseDecorPU");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String url = Constant.LOGIN_PAGE;
            if (session.getAttribute(Constant.ATT_ADMIN) != null) {
                url = Constant.ACCOUNT_INFO_PAGE;
                int userId = Integer.parseInt(request.getParameter(Constant.PARAM_USER_ID));
                //Load account information
                List<Tbluser> listUser = (List<Tbluser>) session.getAttribute(Constant.ATT_ACCOUNT_LIST);
                AccInfoDTO dto = new AccInfoDTO();
                for (int i = 0; i < listUser.size(); i++) {
                    if (userId == listUser.get(i).getUserID()) {
                        //set userID
                        dto.setUserId(listUser.get(i).getUserID());
                        //set fullname
                        dto.setFullname(listUser.get(i).getLastname() + " " + listUser.get(i).getFirstname());
                        //set birth date
                        dto.setBirthdate(listUser.get(i).getDateOfBirth());
                        //set gender
                        dto.setGender(listUser.get(i).getGender());
                        //set phone number
                        dto.setPhoneNumber(listUser.get(i).getPhoneNumber());
                        //set email
                        dto.setEmail(listUser.get(i).getEmail());
                        //set address 1
                        dto.setAddress1(listUser.get(i).getPrimayryAddress());
                        //set address 2
                        dto.setAddress2(listUser.get(i).getSencondAddress());
                        //set role
                        dto.setRole(listUser.get(i).getRoleID());
                        //set register date
                        dto.setRegisterDate(listUser.get(i).getRegisterDate());
                        //set about me
                        dto.setAboutMe(listUser.get(i).getAboutMe());
                        //set list project
                        TblprojectJpaController projectJpa = new TblprojectJpaController(emf);
                        List<ProjectIdNameDTO> listProjectDTO = projectJpa.getProjectIdAndNameByUserId(userId);
                        dto.setListProject(listProjectDTO);
                        //set status
                        dto.setStatus(listUser.get(i).getStatus());
                        break;
                    }
                }
                request.setAttribute(Constant.ATT_DETAIL, dto);
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            log("ERROR at " + Constant.LOAD_ACCOUNT_INFO_SERVLET + ": " + e.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
