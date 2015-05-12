/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:54:04 AM  : Mar 29, 2015
 */
package controllers.services;

import domain.entities.Manuscript;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author kelli Perform all operations on Manuscripts linked to the datasource
 * ; Listing, editing, searches and deleting. (use connection to datasource for
 * all connection)
 */
public class ManuscriptOpsService {

    private final static Logger logger = Logger.getLogger(ManuscriptOpsService.class.getName());
    private EntityManager em;

    public List<Manuscript> getManuscripts(int startPage) {
        em = Connection.getConnector();
        if (em == null) {
            logger.log(Level.SEVERE, "The entitymanager is null");
        }
        logger.log(Level.INFO, "Inside getManuscripts");
        List<Manuscript> results;
        Query query = em.createNamedQuery("Manuscript.findAll");
        query.setFirstResult(startPage);
        query.setMaxResults(PageCounter.ROWS_PER_PAGE);
        results = query.getResultList();
        logger.log(Level.INFO, "manuscripts  in the list: {0}", results.size());
        return results;
    }

    public List<Manuscript> search(String authorName) {
        em = Connection.getConnector();
        List<Manuscript> searchResult = null;
        Query searchQuery = em.createNamedQuery("Manuscript.findByAuthor");
        searchQuery.setParameter("surname", authorName);
        searchResult = searchQuery.getResultList();
        return searchResult;
    }

    /**
     *
     * @param column the column to target for the filter
     * @param criteria the criteria to use for filter
     * @param page
     * @return
     */
    public List<Manuscript> filter(String column, String criteria, int page) {
        em = Connection.getConnector();
        String targetQuery, queryParam;
        List<Manuscript> filterResults;
        switch (column) {
            case "status":
                targetQuery = "Manuscript.findByStatus";
                queryParam = "status";
                break;
            case "year":
                targetQuery = "Manuscripts.findByDateSubmitted";
                queryParam = "dateSubmitted";
                break;
            case "keywords":
                logger.log(Level.INFO, "inside keywords");
                targetQuery = "Manuscript.nativeFindByTitle";
                queryParam = "1";
                break;
            case "affiliation":
                targetQuery = "Manuscript.findByAffiliation";
                logger.log(Level.INFO, "inside affiliation");
                queryParam = "affiliation";
                break;
            default:
                targetQuery = "Manuscript.findByStatus";
                queryParam = "status";
                break;
        }//end switch
//        filterResults = prepareQuery(targetQuery, queryParam, criteria);
        logger.log(Level.INFO, "queery: {0}", targetQuery);
        logger.log(Level.INFO, "queryparam: {0}", queryParam);
        logger.log(Level.INFO, "criteria: {0}", criteria);
        logger.log(Level.INFO, "page: {0}", page);
        Query searchQuery = em.createNamedQuery(targetQuery);
        searchQuery.setParameter(queryParam, criteria);
        //always return filter results in sets of 10
        searchQuery.setFirstResult((page-1)*PageCounter.ROWS_PER_PAGE);//increment view rows by 10
        searchQuery.setMaxResults(PageCounter.ROWS_PER_PAGE);
        filterResults = searchQuery.getResultList();
        return filterResults;
    }

//    /**
//     * Formulate a query based on the user selection the user made...
//     *
//     * @param targetColumn
//     * @param parameter
//     * @param parameterValue
//     * @return
//     */
//    private List<Manuscript> prepareQuery(String targetColumn, String parameter, String parameterValue) {
//        Query searchQuery = em.createNamedQuery(targetColumn);
//        searchQuery.setParameter(parameter, parameterValue);
//        searchQuery.setMaxResults(PageCounter.ROWS_PER_PAGE);
//        List<Manuscript> filterResults = searchQuery.getResultList();
//        return filterResults;
//    }
    public List<Manuscript> search(int description) {
        List<Manuscript> result = null;
        return result;
    }

    public boolean createManuscript(String[] manuscriptDetails) {
        boolean isCreated = false;
        return isCreated;
    }

    public boolean editmanuscript(int manuscriptId) {
        boolean updated = false;
        return updated;
    }

    public boolean delete(int manuscriptId) {
        boolean isDeleted = false;
        return isDeleted;
    }
}
