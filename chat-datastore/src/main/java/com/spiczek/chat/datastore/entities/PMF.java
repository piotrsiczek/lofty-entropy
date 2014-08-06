package com.spiczek.chat.datastore.entities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PMF {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("transaction-optional");

    private PMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
