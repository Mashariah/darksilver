/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:35:54 AM  : Mar 30, 2015
 */
package controllers.services;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author kelli calculate the number of pages to be displayed to the user. from
 * the
 */
public class PageCounter {

    static final int ROWS_PER_PAGE = 10;
//        private final int pageCounter=1;

    public int getCurrentPage() {
        int page = 1;
        return page;
    }

    /**
     * Get the total number of pages required to display all records in the manuscripts table 
     * 10 records per page.
     * @param sqlStmt
     * @return number of pages
     */
    public static int calcTotalPages(String sqlStmt) {

        EntityManager em = Connection.getConnector();
        Query counterQuery = em.createNativeQuery(sqlStmt);
        Long rowCount = (Long) counterQuery.getSingleResult();
        int totalPages = Math.round(rowCount / ROWS_PER_PAGE);
        return totalPages + 1;
    }
}
