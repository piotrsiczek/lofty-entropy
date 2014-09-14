package com.spiczek.chat.datastore.daos;

import com.google.common.collect.Iterables;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.spiczek.chat.datastore.entities.Chat;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.spiczek.chat.datastore.OfyService.ofy;


/**
 * @author Piotr Siczek
 */
public class MessageDAO {

    public Key<Talk> createTalk(Long userKey, Long userChatKey, Long receiverChatKey, Long receiverKey) {
        Talk talk = new Talk(Key.create(User.class, userKey), Key.create(User.class, receiverKey));
        Key<Talk> talkKey = ofy().save().entity(talk).now();

        Collection<Chat> chats = ofy().load().keys(Key.create(Chat.class, userChatKey), Key.create(Chat.class, receiverChatKey)).values();
        Chat myChat = Iterables.get(chats, 0);
        Chat receiverChat = Iterables.get(chats, 1);

        myChat.setTalk(talkKey);
        receiverChat.setTalk(talkKey);
        ofy().save().entities(myChat, receiverChat).now();

        return talkKey;
    }

    public Key<Message> createMessage(String text, Long talkKey, Long senderKey) {
        Message message = new Message(text, Key.create(User.class, senderKey));
        Key<Message> messageKey = ofy().save().entity(message).now();

        Talk talk = ofy().load().key(Key.create(Talk.class, talkKey)).now();
        talk.setMessage(messageKey);
        ofy().save().entity(talk);

        return messageKey;
    }

    public List<Talk> getTalk(Long userChatKey, Long reciverKey) {
        Chat chat = ofy().load().key(Key.create(Chat.class, userChatKey)).now();
        Query<Talk> q = ofy().load().type(Talk.class);
        List<Key<User>> dudes = new ArrayList<Key<User>>();
        dudes.add(Key.create(User.class, reciverKey));
        List<Talk> talks = q.filter("dudes in", dudes).filterKey("in", chat.getTalks()).list();

        return talks;
    }

    public List<Message> getMessages(Long talkKey) {
        Talk talk = ofy().load().key(Key.create(Talk.class, talkKey)).now();
        List<Message> c = new ArrayList(ofy().load().keys(talk.getMessages()).values());
        return c.size() == 0 ? null : c;
    }

    public List<User> getTalkDudes(List<Key<User>> dudes) {
        return ofy().load().type(User.class).filterKey("in", dudes).list();
    }
}