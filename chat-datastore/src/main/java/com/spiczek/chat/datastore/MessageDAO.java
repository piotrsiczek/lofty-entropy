package com.spiczek.chat.datastore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.spiczek.chat.datastore.entities.Chat;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.datastore.entities.User;

import java.util.ArrayList;
import java.util.List;

import static com.spiczek.chat.datastore.OfyService.ofy;


/**
 * @author Piotr Siczek
 */
public class MessageDAO {

    public Key<Talk> createTalk(Chat myChat, Chat reciverChat, Key<User> reciver) {
        Talk talk = new Talk(reciver);
        Key<Talk> talkKey = ofy().save().entity(talk).now();

        myChat.setTalk(talkKey);
        reciverChat.setTalk(talkKey);

        ofy().save().entities(myChat).now();
        ofy().save().entities(reciverChat).now();
        return talkKey;
    }

    public void createMessage(String text, Talk talk, Key<User> senderKey) {
        Message message = new Message(text, senderKey);
        Key<Message> messageKey = ofy().save().entity(message).now();
        talk.setMessage(messageKey);
        ofy().save().entity(talk);
    }

    public List<Talk> getTalk(User sender, User reciver) {
        Chat chat = ofy().load().key(sender.getChat()).now();
        Query<Talk> q = ofy().load().type(Talk.class);
        List<Talk> talks = q.filter("dude", Key.create(reciver)).filterKey("in", chat.getTalks()).list();

        return talks;
    }

    public List<Message> getMessages(Talk talk) {
        List<Message> c = new ArrayList(ofy().load().keys(talk.getMessages()).values());
        return c;
    }

    public void test() {
        UserDAO userDAO = new UserDAO();
        User u1 = userDAO.createUser("a", "a");
        User u2 = userDAO.createUser("b", "b");

        Chat c = ofy().load().key(u1.getChat()).now();
        Chat c2 = ofy().load().key(u2.getChat()).now();

        Key<Talk> talkKey = createTalk(c, c2, Key.create(u2));
        Talk t = ofy().load().key(talkKey).now();

        createMessage("test1", t, Key.create(u1));
        createMessage("test2", t, Key.create(u1));
        createMessage("test3", t, Key.create(u1));
        createMessage("test4", t, Key.create(u1));
        createMessage("test4", t, Key.create(u1));
        createMessage("test4", t, Key.create(u1));
        createMessage("test1", t, Key.create(u1));
        createMessage("test2", t, Key.create(u1));
        createMessage("test3", t, Key.create(u1));
        createMessage("test4", t, Key.create(u1));
        createMessage("test4", t, Key.create(u1));
        createMessage("test4", t, Key.create(u1));

        Key<Talk> talkKey2 = createTalk(c, c2, Key.create(u2));
        Talk t2 = ofy().load().key(talkKey2).now();

        createMessage("test1", t2, Key.create(u1));
        createMessage("test2", t2, Key.create(u1));
        createMessage("test3", t2, Key.create(u1));
        createMessage("test4", t2, Key.create(u1));
        createMessage("test4", t2, Key.create(u1));
        createMessage("test4", t2, Key.create(u1));

        List<Talk> talks = getTalk(u1, u2);
    }
}
