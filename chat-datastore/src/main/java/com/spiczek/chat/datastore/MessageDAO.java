package com.spiczek.chat.datastore;

import com.googlecode.objectify.Key;
import com.spiczek.chat.datastore.entities.Chat;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.datastore.entities.User;

import static com.spiczek.chat.datastore.OfyService.ofy;


/**
 * @author Piotr Siczek
 */
public class MessageDAO {

    public Key<Talk> createTalk(Chat myChat, Key<User> reciver) {
        Talk talk = new Talk(reciver);
        Key<Talk> talkKey = ofy().save().entity(talk).now();
        myChat.setTalk(talkKey);
        ofy().save().entities(myChat);
        return talkKey;
    }

    public void createMessage(String text, Talk talk, Key<User> senderKey) {
        Message message = new Message(text, senderKey);
        Key<Message> messageKey = ofy().save().entity(message).now();
        talk.setMessage(messageKey);
        ofy().save().entity(talk);
    }

    public void test() {
        UserDAO userDAO = new UserDAO();
        User u1 = userDAO.createUser("a", "a");
        User u2 = userDAO.createUser("b", "b");

        Chat c = ofy().load().key(u1.getChat()).now();



        Key<Talk> talkKey = createTalk(c, Key.create(u2));

        Talk t = ofy().load().key(talkKey).now();

        createMessage("test", t, Key.create(u1));
    }
}
