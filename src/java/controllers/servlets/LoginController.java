/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers.servlets;

import controllers.services.Authentication;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kelli
 */
//@WebServlet(name = "LoginController" urlPatterns={"admin/login_control"});

public class LoginController extends HttpServlet {

    private final static Logger lg = Logger.getLogger(LoginController.class.getName());
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
            lg.entering(getClass().getName(), "processRequest");
            lg.setLevel(Level.ALL);
            
      
        /**
         * Validate user login details and return admin page
         */
        String userName=request.getParameter("tx_admin_user");
        String password=request.getParameter("tx_admin_password");
        lg.log(Level.INFO, "User:  "+userName);
        lg.log(Level.INFO, "Password: "+ password);
        boolean isValid = Authentication.validateUser(userName, password);
        if(isValid){
                lg.info("Login Sucess!");
                //create a session and add username to the object
                HttpSession session = request.getSession(true);
                session.setAttribute("user", userName);
                //test the session 
                request.getRequestDispatcher("/admin/new_author.jsp").forward(request, response);
            }
        else{
                lg.info("Login Fail");
                request.setAttribute("login_error", "wrong username or password");
                request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
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
