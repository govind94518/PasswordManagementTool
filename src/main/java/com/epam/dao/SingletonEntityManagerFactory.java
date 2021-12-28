package com.epam.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class  SingletonEntityManagerFactory {
    private static final String PERSISTENCE_UNIT_NAME = "EmployeeJPADB";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getInstance() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }
}