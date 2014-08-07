package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.NoArgsConstructor;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
@NoArgsConstructor
public class User {
    @Id Long id;
    String name;
    String surname;
    Key<Friend> friend;
    Key<Chat> chat;

    public User(String name, String surname, Key<Friend> friendKey, Key<Chat> chatKey) {
        this.name = name;
        this.surname = surname;
        this.friend = friendKey;
        this.chat = chatKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setFriend(Key<Friend> friend) {
        this.friend = friend;
    }

    public Key<Chat> getChat() {
        return chat;
    }

    public void setChat(Key<Chat> chat) {
        this.chat = chat;
    }
}