/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 10:37:25 PM  : Mar 30, 2015
 */
package controllers.servlets;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import controllers.services.ManuscriptOpsService;
import domain.entities.Manuscript;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kelli
 *
 * Search for manuscripts: (a) By the text entered in the search panel (b) By
 * the criteria selected in the filter panel in the manuscripts page. Method to
 * execute will be determined by the presence of a flag on the request url.
 */
public class SearchManuscripts extends HttpServlet {

    private final static Logger logger = Logger.getLogger(SearchManuscripts.class.getName());
    private final ManuscriptOpsService ms = new ManuscriptOpsService();
    private List<Manuscript> searchResults;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * Establish the type of search requested based on the presence of a
         * search_type flag in the request url.
         */
        int searchTypeFlag = Integer.parseInt(request.getParameter("search_type"));
        logger.log(Level.INFO, "type flag is:{0}", searchTypeFlag);

        switch (searchTypeFlag) {
            case 1:
                String target = request.getParameter("txt_search");
                searchResults = searchByText(target);
                //redirect to the manuscripts page...
                request.setAttribute("m_list", searchResults);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/manuscripts.jsp");
                dispatcher.forward(request, response);
                break;

            case 2:
                String filterCriteria = request.getParameter("filter_criteria");
                String filterTarget = request.getParameter("filter_target");
                int  page = Integer.parseInt(request.getParameter("page"));
                logger.log(Level.INFO, "Criteria ={0}", filterCriteria);
                logger.log(Level.INFO, "Target ={0}", filterTarget);
                if (filterCriteria != null && filterTarget != null) {
                    String output = searchByFilter(filterTarget, filterCriteria,page);
                    PrintWriter writer = response.getWriter();
                    writer.print(output); //write the JSON output
                    logger.info("output is" + output);
                    writer.flush();
                }
                break;

            default:
            //flag value not found...list searchResults remains null
        }//end switch

    }

    private List searchByText(String targetUrl) {
        List<Manuscript> results = ms.search(targetUrl);
        return results;
    }

    /**
     * Filter manuscripts list based on selected criteria
     *
     * @param filterTarget the column to be filtered
     * @param filterText the criteria to be used for the filter
     * @return JSON string with results list
     */
    private String searchByFilter(String filterTarget, String filterText, int page) {
        String jsonResults = null;
        JSONArray array = new JSONArray();
        List<Manuscript> results = ms.filter(filterTarget, filterText,page);
        /*
         For all manuscript items in this list... create  a JSON object and add it to the 
         JSON array instance.
         */
        logger.log(Level.INFO, "Items size returned by filter: {0}", results.size());
        
        for (Manuscript manuscript : results) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title", manuscript.getTitle());
                jsonObject.put("ref_no", manuscript.getManuscriptRefNumber());
                jsonObject.put("other_details", manuscript.getAuthorId().getSurname() + ", "
                        + manuscript.getAuthorId().getInitials() +" \n "+ manuscript.getStatus());
                array.put(jsonObject);
            } catch (JSONException ex) {
                Logger.getLogger(SearchManuscripts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jsonResults = array.toString();
        return jsonResults;
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
