package com.spiczek.chat.datastore;

import com.google.storage.onestore.v3.OnestoreEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.spiczek.chat.datastore.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.spiczek.chat.datastore.OfyService.ofy;


/**
 * @author Piotr Siczek
 */
public class UserDAO {

    public User createUser(String name, String surname) {
        Key<Friend> friendKey = ofy().save().entity(new Friend()).now();
        Key<Chat> chatKey = ofy().save().entity(new Chat()).now();

        User u = new User(name, surname, friendKey, chatKey);
        ofy().save().entities(u).now();
        return u;
    }

    public Friend createFriend(User user, User friend) {
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();

        friendEntity.setFriends(Key.create(friend));
        ofy().save().entities(friendEntity);

        return friendEntity;
    }

    public List<User> getFriends(User user) {
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Collection<User> collection = ofy().load().keys(friendEntity.getFriends()).values();

        return new ArrayList(collection);
    }

    public List<User> getFriends(User user, Long startId, int length) {
        Key<User> startKey = Key.create(User.class, startId);
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Query<User> q = ofy().load().type(User.class);
        q = q.filterKey("in", friendEntity.getFriends());
        q = q.filterKey(">", startKey).limit(length);
        q = q.limit(length);

        return q.list();
    }
}
