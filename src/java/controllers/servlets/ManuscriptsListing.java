/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:07:25 AM  : Mar 30, 2015
 */
package controllers.servlets;

import controllers.services.ManuscriptOpsService;
import controllers.services.PageCounter;
import domain.entities.Manuscript;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kelli Lists all manuscript records; using paged output.
 */
public class ManuscriptsListing extends HttpServlet {

    private final static Logger logger = Logger.getLogger(ManuscriptsListing.class.getName());
    private String[] logParams = {ManuscriptsListing.class.getName()};

    private ManuscriptOpsService service;
    private List<Manuscript> manuscriptsListing;
    final int ROWS_PER_PAGE = 10;
    private final String recordsCount = "select count(manuscript_id) from manuscripts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.log(Level.INFO, "Inside {0}: ", logParams[0]);

        int pageCounter = 1;
        service = new ManuscriptOpsService();

        //process subsequent page requests...
        String pageRequest = request.getParameter("page");

        logger.log(Level.INFO, "{0}: Page param  value is: {1}", logParams);
        if (pageRequest != null) {
            pageCounter = Integer.parseInt(pageRequest);
        }

        manuscriptsListing = service.getManuscripts((pageCounter - 1) * ROWS_PER_PAGE);
        //send the page number and results to user
        logger.log(Level.INFO, "Items in the manuscripts list: {0}", manuscriptsListing.size());
        //set year for publication date for each manuscript
        for (Manuscript manuscript : manuscriptsListing) {
            Date date = manuscript.getDateSubmitted();
            String formattedDate = getYear(date);
            manuscript.setYear(formattedDate);
        }
        request.setAttribute("m_list", manuscriptsListing);
        request.setAttribute("currentPage", pageCounter);

        //get the number of pages to navigate
        int totalPages = PageCounter.calcTotalPages(recordsCount);
        request.setAttribute("totalPages", totalPages);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/manuscripts.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Format each date in the manuscripts list to show in format 'yyyy'
     */
    private String getYear(Date d) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        String year = sd.format(d);
        return year;
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
