package com.spiczek.chat.datastore;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.spiczek.chat.datastore.entities.Friend;
import com.spiczek.chat.datastore.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Piotr Siczek
 */
public class UserDaoTest {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private UserDAO data = new UserDAO();
    private static final String USER_NAME = "user_name";
    private static final String USER_SURNAME = "user_surname";
    private static final String FRIEND_USER_NAME = "friend_user_name";
    private static final String FRIEND_USER_SURNAME = "friend_user_surname";

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

    @Test
    public void createFriendResultTest() {
        User u = data.createUser(USER_NAME, USER_SURNAME);
        User friend = data.createUser(FRIEND_USER_NAME, FRIEND_USER_SURNAME);
        Friend friendEntity = data.createFriend(u, friend);

        assertEquals(friendEntity.getId()+"", u.getFriend().getId()+"");
    }

    @Test
    public void createFriendInDatastoreTest() {
        User u = data.createUser(USER_NAME, USER_SURNAME);
        User friend = data.createUser(FRIEND_USER_NAME, FRIEND_USER_SURNAME);
        data.createFriend(u, friend);

        Key<Friend> friendKey = u.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Collection<User> collection = ofy().load().keys(friendEntity.getFriends()).values();
        List<User> l = new ArrayList(collection);
        User result = l.get(0);

        assertEquals(collection.size(), 1);
        assertEquals(result.getName(), FRIEND_USER_NAME);
        assertEquals(result.getSurname(), FRIEND_USER_SURNAME);

        assertNotNull(result.getId());
        assertNotNull(result.getChat());
        assertNotNull(result.getFriend());
    }

    @Test
    public void getFriendsTest() {
        User u = data.createUser(USER_NAME, USER_SURNAME);
        data.createFriend(u, data.createUser(FRIEND_USER_NAME, FRIEND_USER_SURNAME));
        data.createFriend(u, data.createUser(FRIEND_USER_NAME, FRIEND_USER_SURNAME));
        data.createFriend(u, data.createUser(FRIEND_USER_NAME, FRIEND_USER_SURNAME));

        List<User> result = data.getFriends(u);
        assertEquals(result.size(), 3);

    }

}
