package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
@NoArgsConstructor
public class User {
    @Id private Long id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;

    private Key<Friend> friend;
    private Key<Chat> chat;

    public User(String name, String surname, String login, String email, String password, Key<Friend> friendKey, Key<Chat> chatKey) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.email = email;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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