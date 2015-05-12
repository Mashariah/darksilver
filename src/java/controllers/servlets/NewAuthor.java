/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 5:39:57 PM  : Mar 12, 2015
 */

package controllers.servlets;

import controllers.services.Connection;
import domain.entities.Author;
import java.io.IOException;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kelli
 */
public class NewAuthor extends HttpServlet {

        private final static Logger logger= Logger.getLogger(NewAuthor.class.getName());

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
            
        /**
         * Get all parameters from the form and create new Author
         * Persist author to db
         * Forward UI to user with continue option or display
         * 
         */
        String surname = request.getParameter("tx_surname");
        String initials = request.getParameter("tx_initials");
        String affiliation = request.getParameter("tx_affiliation");
        String email = request.getParameter("tx_email");
        String phone = request.getParameter("tx_phone");
        
        //create new author
//        logger.entering(this.getClass().getName(), NewAuthor.class.getEnclosingMethod().getName());
        Author author = new Author();
        author.setSurname(surname);
        author.setInitials(initials);
        author.setAffiliation(affiliation);
        author.setEmail(email);
        author.setPhone(phone);
        //connect to database and add record
        EntityManager em = Connection.getConnector();
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();
        logger.info("new author commited to the database");
        Connection.closeConnector(em);
        
        //save success? forward to authors list and alter the save buttons and show javascript popoup success.
        request.setAttribute("author_save", true);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main.sx");
        dispatcher.forward(request, response);
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
