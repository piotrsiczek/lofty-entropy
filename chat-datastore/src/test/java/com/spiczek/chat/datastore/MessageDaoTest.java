package com.spiczek.chat.datastore;

import com.google.appengine.labs.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.spiczek.chat.datastore.daos.MessageDAO;
import com.spiczek.chat.datastore.daos.UserDAO;
import com.spiczek.chat.datastore.entities.Chat;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.datastore.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.spiczek.chat.datastore.OfyService.ofy;
import static org.junit.Assert.*;

/**
 * @author Piotr Siczek
 */
public class MessageDaoTest {
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private MessageDAO messageDao = new MessageDAO();
    private UserDAO userDao = new UserDAO();

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void createTalkTest() {
        List<User> users = createUsers(2);
        User u = users.get(0);
        User uu = users.get(1);

        Key<Talk> talkKey = messageDao.createTalk(u.getId(), u.getChat().getId(), uu.getChat().getId(), uu.getId());
        Collection<Chat> chats = ofy().load().keys(u.getChat(), uu.getChat()).values();

        assertNotNull(talkKey);
        assertEquals(talkKey, Iterables.get(chats, 0).getTalks().get(0));
        assertEquals(talkKey, Iterables.get(chats, 1).getTalks().get(0));
    }

    @Test
    public void createMessageTest() {
        List<User> users = createUsers(2);
        User u = users.get(0);
        User uu = users.get(1);

        Key<Talk> talkKey = messageDao.createTalk(u.getId(), u.getChat().getId(), uu.getChat().getId(), uu.getId());
        Key<Message> messageKey = messageDao.createMessage("test1", Consts.TIME, talkKey.getId(), u.getId());

        assertNotNull(messageKey);
    }

    @Test
    public void getTalkTest() {
        List<User> users = createUsers(2);
        User u = users.get(0);
        User uu = users.get(1);

        messageDao.createTalk(u.getId(), u.getChat().getId(), uu.getChat().getId(), uu.getId());
        List<Talk> talks = messageDao.getTalk(u.getChat().getId(), uu.getId());

        assertNotNull(talks);
        assertTrue(talks.size() == 1);
    }

    @Test
    public void getMessageTest() {
        List<User> users = createUsers(2);
        User u = users.get(0);
        User uu = users.get(1);

        Key<Talk> talkKey = messageDao.createTalk(u.getId(), u.getChat().getId(), uu.getChat().getId(), uu.getId());
        messageDao.createMessage(Consts.CHAT_MESSAGE, Consts.TIME, talkKey.getId(), u.getId());
        List<Message> messages = messageDao.getMessages(talkKey.getId());

        assertNotNull(messages);
        assertEquals(Consts.CHAT_MESSAGE, messages.get(0).getText());
    }

    @Test
    public void getMessageThatDoesntExistTest() {
        List<User> users = createUsers(2);
        User u = users.get(0);
        User uu = users.get(1);

        Key<Talk> talkKey = messageDao.createTalk(u.getId(), u.getChat().getId(), uu.getChat().getId(), uu.getId());
        List<Message> messages = messageDao.getMessages(talkKey.getId());

        assertNull(messages);
    }

    private List<User> createUsers(int size) {
        List<User> users = new ArrayList<User>();
        for (int i=0; i < size; i++) {
            User u = userDao.createUser(Consts.USER_NAME, Consts.USER_SURNAME, Consts.USER_LOGIN + i, "", "");
            users.add(u);
        }
        return users;
    }
}
