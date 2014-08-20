package com.spiczek.chat.datastore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.spiczek.chat.datastore.Integration.DataIntegration;
import com.spiczek.chat.datastore.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.spiczek.chat.datastore.OfyService.ofy;


/**
 * @author Piotr Siczek
 */
public class UserDAO {

    public User createUser(String name, String surname, String login, String email, String password) {
        Key<Friend> friendKey = ofy().save().entity(new Friend()).now();
        Key<Chat> chatKey = ofy().save().entity(new Chat()).now();

        User u = new User(name, surname, login, email, password, friendKey, chatKey);
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

    public List<User> getFriends(Long friendId) {
        Friend friendEntity = ofy().load().type(Friend.class).id(friendId).now();
        List<Key<User>> friendKeys = friendEntity.getFriends();

        return (friendKeys.size() == 0) ? null : ofy().load().type(User.class).filterKey("in", friendKeys).list();
    }



    public List<User> getFriends(User user) {
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Collection<User> collection = ofy().load().keys(friendEntity.getFriends()).values();

        return new ArrayList(collection);
    }

    public List<User> getFriends(User user, Long startId, int length) {
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Query<User> q = ofy().load().type(User.class);
        q = q.filterKey("in", friendEntity.getFriends());
        if (startId != null) {
            Key<User> startKey = Key.create(User.class, startId);
            q = q.filterKey(">", startKey).limit(length);
        }
        q = q.limit(length);

        return q.list();
    }

    public User loginUser(String username, String password) {
        Query<User> q = ofy().load().type(User.class);
        q = q.filter("login", username);
        q = q.filter("password", password);
        List<User> result = q.list();
        return result.size() == 1 ? result.get(0) : null;
    }

    public User findUser(String login) {
        Query<User> q = ofy().load().type(User.class);
        q = q.filter("login", login);
        if (q.list().size() == 0)
            return null;

        return q.list().get(0);
    }

    public void test(Long id) {

        User u = ofy().load().type(User.class).id(id).now();
        DataIntegration.generateFriend(u, 100);
    }
}
