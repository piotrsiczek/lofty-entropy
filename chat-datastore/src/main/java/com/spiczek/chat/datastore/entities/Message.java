package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
public class Message {
    @Id Long id;
    String text;
    Key<User> sender;

    public Message() {}

    public Message(String text, Key<User> sender) {
        this.text = text;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public Key<User> getSender() {
        return sender;
    }
}
