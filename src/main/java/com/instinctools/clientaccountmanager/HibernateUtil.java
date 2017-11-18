package com.instinctools.clientaccountmanager;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration; 
 
public class HibernateUtil { 
    private static final SessionFactory sessionFactory = buildSessionFactory(); 
    private static final Logger LOGGER = LogManager.getLogger(SessionFactory.class);
 
    private static SessionFactory buildSessionFactory() { 
        try { 
            return new Configuration().configure( 
                    new File("hibernate.cfg.xml")).buildSessionFactory(); 
 
        } catch (Throwable ex) { 
        	LOGGER.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex); 
        } 
    } 
 
    public static SessionFactory getSessionFactory() { 
        return sessionFactory; 
    } 
 
    public static void shutdown() { 
        // Close caches and connection pools 
        getSessionFactory().close(); 
    } 
} 