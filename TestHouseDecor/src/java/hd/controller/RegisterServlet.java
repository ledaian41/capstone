/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd.controller;

import hd.DAO.CityDAO;
import hd.DAO.UserDAO;
import hd.JPA.CityJpaController;
import hd.JPA.ProfessionalJpaController;
import hd.JPA.RoleJpaController;
import hd.JPA.TypeOfWorkJpaController;
import hd.JPA.exceptions.PreexistingEntityException;
import hd.entity.City;
import hd.entity.Professional;
import hd.entity.Role;
import hd.entity.TypeOfWork;
import hd.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author cuk3t
 */
public class RegisterServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */

            String url = "";
            String email = request.getParameter("email");
            UserDAO dao = new UserDAO();
            User user = dao.getUserByEmail(email);
            if (user != null) {
                request.setAttribute("emailIsExist", "Email is exist in system.");
                url = "register.jsp";

            } else {
                String password = request.getParameter("password");
                String firstname = request.getParameter("firstName");
                String lastname = request.getParameter("lastName");
                String dateStr = request.getParameter("birthDay");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                java.util.Date date = null;
                date = sdf1.parse(dateStr);
                java.sql.Date birthdate = new Date(date.getTime());
                //Date birthday = Date.valueOf(date);
                String phone = request.getParameter("phone");
                String gender = request.getParameter("gender");
                boolean gender1 = gender.equals("1");
                String cityCode = request.getParameter("city");
                CityJpaController cityJpa = new CityJpaController(emf);
                City city = cityJpa.findCity(cityCode);
                int roleId = Integer.parseInt(request.getParameter("txtRoleId"));
                RoleJpaController roleJpa = new RoleJpaController(emf);
                Role role = roleJpa.findRole(roleId);
                String address = request.getParameter("address");
                dao.insertMember(email, password, lastname, firstname, birthdate, phone, gender1, city, address, role);

                HttpSession session = request.getSession();
                user = dao.getUserByEmail(email);
                session.setAttribute("user", user);
                url = "LoginServlet";

                if (roleId == Constant.ROLE_PROFESSIONAL) {
                    ProfessionalJpaController proJpa = new ProfessionalJpaController(emf);
                    TypeOfWorkJpaController workJpa = new TypeOfWorkJpaController(emf);
                    int workId = Integer.parseInt(request.getParameter("txtTypeOfWork"));
                    TypeOfWork work = workJpa.findTypeOfWork(workId);
                    Professional pro = new Professional(user.getUserId());
                    pro.setProfessionalName(lastname + " " + firstname);
                    pro.setAddress(address);
                    pro.setTypeOfWorkId(work);
                    proJpa.create(pro);
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            log("Error at RegisterServlet: " + e.getMessage());
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
