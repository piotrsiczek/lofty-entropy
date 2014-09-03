package com.spiczek.chat.datastore;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.spiczek.chat.datastore.daos.UserDAO;
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
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private UserDAO data = new UserDAO();


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
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");

        assertEquals(u.getName(), Consts.USER_NAME);
        assertEquals(u.getSurname(), Consts.USER_SURNAME);
        assertNotNull(u.getId());
        assertNotNull(u.getChat());
        assertNotNull(u.getFriend());
    }

    @Test
    public void createUserInDatastoreTest() {
        Key<User> userKey = Key.create(data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", ""));
        assertNotNull(userKey);
        User u = ofy().load().key(userKey).now();

        assertEquals(u.getName(), Consts.USER_NAME);
        assertEquals(u.getSurname(), Consts.USER_SURNAME);

        assertNotNull(ofy().load().key(u.getFriend()).now());
        assertNotNull(ofy().load().key(u.getChat()).now());
    }

    @Test
    public void createUserThatExistsInDatastoreTest() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        User uu = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        assertNull(uu);
    }

    @Test
    public void createFriendResultTest() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        User friend = data.createUser(Consts.FRIEND_USER_NAME, Consts.FRIEND_USER_SURNAME, Consts.FRIEND_USER_LOGIN, "", "");
        Friend friendEntity = data.createFriend(u.getFriend().getId(), friend);

        assertEquals(friendEntity.getId()+"", u.getFriend().getId()+"");
    }

    @Test
    public void createFriendThatExistsTest() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        User friend = data.createUser(Consts.FRIEND_USER_NAME, Consts.FRIEND_USER_SURNAME, Consts.FRIEND_USER_LOGIN, "", "");
        data.createFriend(u.getFriend().getId(), friend);
        Friend result = data.createFriend(u.getFriend().getId(), friend);

        assertNull(result);
    }

    @Test
    public void createFriendInDatastoreTest() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        User friend = data.createUser(Consts.FRIEND_USER_NAME, Consts.FRIEND_USER_SURNAME, Consts.FRIEND_USER_LOGIN, "", "");
        data.createFriend(u.getFriend().getId(), friend);

        Key<Friend> friendKey = u.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Collection<User> collection = ofy().load().keys(friendEntity.getFriends()).values();
        List<User> l = new ArrayList(collection);
        User result = l.get(0);

        assertEquals(collection.size(), 1);
        assertEquals(result.getName(), Consts.FRIEND_USER_NAME);
        assertEquals(result.getSurname(), Consts.FRIEND_USER_SURNAME);

        assertNotNull(result.getId());
        assertNotNull(result.getChat());
        assertNotNull(result.getFriend());
    }

    @Test
    public void removeFriendThatExistsTest() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        User friend = data.createUser(Consts.FRIEND_USER_NAME, Consts.FRIEND_USER_SURNAME, Consts.FRIEND_USER_LOGIN, "", "");
        data.createFriend(u.getFriend().getId(), friend);

        assertTrue(data.removeFriend(u.getFriend().getId(), friend.getId()));
    }


    @Test
    public void getFriendsTest() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        createFriendsForUser(u, 3);

        List<User> result = data.getFriends(u);
        assertEquals(result.size(), 3);
    }

    @Test
    public void getFriendsRangeTest() {
        int size = 2;
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, "", "");
        List<User> friends = createFriendsForUser(u, 7);
        List<User> result = data.getFriends(u, friends.get(1).getId(), size);

        assertEquals(friends.get(2).getId(), result.get(0).getId());
        assertEquals(friends.get(3).getId(), result.get(1).getId());
        assertEquals(size, result.size());
    }

    @Test
    public void validateValidUser() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, Consts.USER_EMAIL, Consts.USER_PASSWORD);
        assertNotNull(data.loginUser(Consts.USER_LOGIN, Consts.USER_PASSWORD));
    }

    @Test
    public void validateValidPassNotValidLoginUser() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, Consts.USER_EMAIL, Consts.USER_PASSWORD);
        assertNull(data.loginUser("", Consts.USER_PASSWORD));
    }

    @Test
    public void validateValidLoginNotValidPassUser() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, Consts.USER_EMAIL, Consts.USER_PASSWORD);
        assertNull(data.loginUser(Consts.USER_LOGIN, ""));
    }

    @Test
    public void validateNotValidUser() {
        User u = data.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN, Consts. USER_EMAIL, Consts.USER_PASSWORD);
        assertNull(data.loginUser("", ""));
    }

    private List<User> createFriendsForUser(User u, int size) {
        List<User> friends = new ArrayList<User>();
        for (int i=0; i < size; i++) {
            User friend = data.createUser(Consts.FRIEND_USER_NAME+i, Consts.FRIEND_USER_SURNAME+i, Consts.USER_LOGIN+i, "", "");
            data.createFriend(u.getFriend().getId(), friend);
            friends.add(friend);
        }
        return friends;
    }
}
