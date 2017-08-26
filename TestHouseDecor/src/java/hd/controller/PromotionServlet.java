/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.controller;

import hd.DAO.PromotionDAO;
import hd.entity.Promotion;
import hd.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cuk3t
 */
public class PromotionServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(action.equals("show")){
                
                PromotionDAO dao = new PromotionDAO();
                List<Promotion> list = dao.showPromotionBySeler(user.getUserId());
                request.setAttribute("listDTO", list);
                request.getRequestDispatcher("my_promotion.jsp").forward(request, response);
            } else if(action.equals("delete")){
                int promotionID = Integer.parseInt(request.getParameter("txtPromotionId"));
                PromotionDAO dao = new PromotionDAO();
                dao.deletePromotion(promotionID, user.getUserId());
                request.getRequestDispatcher("PromotionServlet?action=show").forward(request, response);
            } else if(action.equals("create")){
                String promotionName = request.getParameter("promotion");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                System.out.println(startDate);
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                try {
                    date1 = sdf1.parse(startDate);
                    date2 = sdf1.parse(endDate);
                } catch (ParseException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date sdate = new Date(date1.getTime());
                java.sql.Date edate = new Date(date2.getTime());
                PromotionDAO dao = new PromotionDAO();
                String description = request.getParameter("description");
                dao.inserPormotion(promotionName, sdate, edate, description, user.getUserId());
                response.sendRedirect("PromotionServlet?action=show");
            }
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

}
