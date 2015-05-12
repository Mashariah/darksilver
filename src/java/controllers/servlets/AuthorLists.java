/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:59:21 AM  : Feb 24, 2015
 */
package controllers.servlets;

import domain.entities.Author;
import controllers.services.Connection;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kelli
 */
public class AuthorLists extends HttpServlet {

    /**
     * Servlet to handle all author operations; create, delete, edit. Use flag
     * in requests to determine the needed operation. Common: Confirm user is
     * logged in for all operations.
     */
    private final static Logger logger = Logger.getLogger(LoginController.class.getName());
    final int ROWS_PER_PAGE = 15;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageCounter = 1;

        //process subsequent page requests...
        String pageRequest = request.getParameter("page");
        if (pageRequest != null) {
            pageCounter = Integer.parseInt(pageRequest);
        }
        logger.info("processing request in AuthorLists");
        List<Author> list = getAll((pageCounter - 1) * ROWS_PER_PAGE); //set results start page
        //send the page number and results to user
        request.setAttribute("author_list", list);   
        request.setAttribute("currentPage", pageCounter);
        
        //get the number of pages to navigate
        int totalPages = calcTotalPages();
        request.setAttribute("totalPages",totalPages);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/authors.jsp");
        dispatcher.forward(request, response);
    }

    public List<Author> getAll(int page) {
        logger.entering("getAll", getClass().getName());
        EntityManager em = Connection.getConnector();
        Query query = em.createNamedQuery("Author.findAll");
        //page the results to 20
        query.setFirstResult(page);
        query.setMaxResults(ROWS_PER_PAGE);
        List<Author> authors = query.getResultList();
        logger.log(Level.INFO, "Items in the list: {0}", authors.size());
        return authors;
    }

    private int calcTotalPages() {

        //TODO: debug the rounding of the totalPages var...
        EntityManager em = Connection.getConnector();
        String countSQLStmt = "select count(author_id) from authors;";
        Query counterQuery = null;
        Long  rowCount;
            counterQuery = em.createNativeQuery(countSQLStmt);
        rowCount = (Long) counterQuery.getSingleResult();
        int totalPages = Math.round(rowCount/ROWS_PER_PAGE);
        return totalPages+1;
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
