/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 6:24:33 PM  : Mar 2, 2015
 */
package controllers.services;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kelli Manage all database connections using JPA
 */
public class Connection {

    private static EntityManager em;
    private static EntityManagerFactory emf;
    private static final String persistenceUnitName = "tracePU";

    private final static Logger logger = Logger.getLogger(Connection.class.getName());

    public static EntityManager getConnector() {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
            logger.info("Created entity manager successfuly in Connector");
        if(em==null){
            logger.severe("Null EM, Failed to create entity manager");
        }
        return em;
    }
    
    public static boolean closeConnector(EntityManager entityManager){
        boolean closed = false;
        
        if(entityManager!=null){
            entityManager.getEntityManagerFactory().close();
            closed = true;
        }
        return closed;
    }
}


