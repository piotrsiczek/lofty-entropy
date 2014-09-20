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
public class Message {
    @Id Long id;
    String text;
    String time;
    Key<User> sender;

    public Message(String text, String time, Key<User> sender) {
        this.text = text;
        this.time = time;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public Key<User> getSender() {
        return sender;
    }
}
