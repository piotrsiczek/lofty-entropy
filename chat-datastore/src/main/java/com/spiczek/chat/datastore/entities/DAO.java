package com.spiczek.chat.datastore.entities;

import javax.persistence.EntityManager;

/**
 * Created by piotr on 2014-07-28.
 */
public class DAO {

    private EntityManager em = PMF.get().createEntityManager();

    public User createUser() {

        User u = new User();
        try {

            u.setName("a");
            u.setSurname("b");

            em.persist(u);
        } finally {
            em.close();
        }
        return u;
    }
}
