package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
@NoArgsConstructor
public class Message {
    @Id Long id;
    String text;
    Date date;
    Key<User> sender;

    public Message(String text, Date date, Key<User> sender) {
        this.text = text;
        this.date = date;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Key<User> getSender() {
        return sender;
    }
}
