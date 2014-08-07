package com.spiczek.chat.datastore;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.spiczek.chat.datastore.entities.Chat;
import com.spiczek.chat.datastore.entities.Friend;
import com.spiczek.chat.datastore.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Piotr Siczek
 */
public class DatastoreTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private DAO data = new DAO();
    private static final String USER_NAME = "user_name";
    private static final String USER_SURNAME = "user_surname";

    @Before
    public void setUp() {

        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }


    @Test
    public void createUserResultTest() {
        User u = data.createUser(USER_NAME, USER_SURNAME);

        assertEquals(u.getName(), USER_NAME);
        assertEquals(u.getSurname(), USER_SURNAME);
        assertNotNull(u.getId());
        assertNotNull(u.getChat());
        assertNotNull(u.getFriend());
    }

    @Test
    public void createUserInDatastoreTest() {
        Key<User> userKey = Key.create(data.createUser(USER_NAME, USER_SURNAME));
        assertNotNull(userKey);
        User u = ofy().load().key(userKey).now();

        assertEquals(u.getName(), USER_NAME);
        assertEquals(u.getSurname(), USER_SURNAME);

        assertNotNull(ofy().load().key(u.getFriend()).now());
        assertNotNull(ofy().load().key(u.getChat()).now());
    }

}
