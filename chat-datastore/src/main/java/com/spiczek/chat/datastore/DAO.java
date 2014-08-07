package com.spiczek.chat.datastore;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.spiczek.chat.datastore.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.factory;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by piotr on 2014-07-28.
 */
public class DAO {

    static {
        factory().register(Friend.class);
        factory().register(User.class);
        factory().register(Talk.class);
        factory().register(Chat.class);
        factory().register(Message.class);
    }

//    private EntityManager em = PMF.get().createEntityManager();
//
//    public User createUser() {
//
//        User u = new User();
//        try {
//
//            u.setName("aasfd");
//            u.setSurname("basdf");
//
//            em.persist(u);
//        } finally {
//            em.close();
//            return u;
//        }
//
//    }
//
//    public void test() {
//
//        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//
//
//        Entity employee = new Entity("Employee");
//
//        employee.setProperty("firstName", "Antonio");
//        employee.setProperty("lastName", "Salieri");
//
//        Date hireDate = new Date();
//        employee.setProperty("hireDate", hireDate);
//
//        employee.setProperty("attendedHrTraining", true);
//
//
//        datastore.put(employee);
//    }



//    public String test3() {
//
//
//        Car car = new Car("abcd", 0);
//        Item item = new Item("item1");
//        Key<Item> itemKey = ofy().save().entity(item).now();
//
//        //car.setItem(item);
//        car.setKey(itemKey);
//        ofy().save().entity(car).now();
//
//
//        Car fetched = ofy().load().entity(car).now();
//        Item i2 = fetched.getItem();
//
//        return car.getId() + "";
//
//    }

    public User createUser(String name, String surname) {
        Key<Friend> friendKey = ofy().save().entity(new Friend()).now();
        Key<Chat> chatKey = ofy().save().entity(new Chat()).now();

        User u = new User(name, surname, friendKey, chatKey);
        ofy().save().entities(u).now();
        return u;
    }

    public void createFriend(User user, User friend) {
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();

        friendEntity.setFriends(Key.create(friend));
        ofy().save().entities(friendEntity);
    }

    public List<User> getFriends(User user) {
        Key<Friend> friendKey = user.getFriend();
        Friend friendEntity = ofy().load().key(friendKey).now();
        Collection<User> collection = ofy().load().keys(friendEntity.getFriends()).values();

        return new ArrayList(collection);
    }

    public void getUser() {

        User u = createUser("name", "surname");
        User dude = createUser("dude2", "dude2");
        User dude2 = createUser("dude3", "dude3");

        createFriend(u, dude);
        createFriend(u, dude2);

        List<User> friends = getFriends(u);

//        Friend f = u.getFriend();
//
//        f.setFriend();
//        ofy().save().entity(f);
    }

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
        User u1 = createUser("a", "a");
        User u2 = createUser("b", "b");

        Chat c = ofy().load().key(u1.getChat()).now();



        Key<Talk> talkKey = createTalk(c, Key.create(u2));

        Talk t = ofy().load().key(talkKey).now();

        createMessage("test", t, Key.create(u1));
    }

}
