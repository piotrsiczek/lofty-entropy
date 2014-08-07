package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
public class User {

    public static class AllFriends {}
    @Id Long id;
    String name;
    String surname;
    Key<Friend> friend;
    Key<Chat> chat;
    @Load List<Ref<Talk>> talks2 = new LinkedList<Ref<Talk>>();


    public User() {}

    public User(String name, String surname, Key<Friend> friendKey, Key<Chat> chatKey) {
        this.name = name;
        this.surname = surname;
        this.friend = friendKey;
        this.chat = chatKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Key<Friend> getFriend() {
        return friend;
    }

//    public void setFriend(Friend friend) {
//        this.friend = Ref.create(friend);
//    }


    public List<Talk> getTalks2() {
        List<Talk> result = new ArrayList<Talk>();
        for (Ref<Talk> t : this.talks2) {
            result.add(t.get());
        }
        return result;
    }

    public void setTalks2(List<Talk> talks) {
        for (Talk t : talks) {
            this.talks2.add(Ref.create(t));
        }
    }

    public Key<Chat> getChat() {
        return chat;
    }

    public void setChat(Key<Chat> chat) {
        this.chat = chat;
    }
}