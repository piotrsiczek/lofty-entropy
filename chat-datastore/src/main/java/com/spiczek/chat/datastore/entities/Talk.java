package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
@NoArgsConstructor
public class Talk {
    @Id Long id;
    Date date;
    List<Key<User>> dudes = new ArrayList<Key<User>>();
    List<Key<Message>> messages = new ArrayList<Key<Message>>();

    public Talk(Key<User>... dude) {
        for (Key<User> u : dude) {
            this.dudes.add(u);
        }
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Key<User>> getDudes() {
        return dudes;
    }

    public void setDudes(List<Key<User>> dudes) {
        this.dudes = dudes;
    }

    public List<Key<Message>> getMessages() {
        return messages;
    }

    public void setMessage(Key<Message> message) {
        this.messages.add(message);
    }

    public void setMessages(List<Key<Message>> messages) {
        this.messages = messages;
    }
}
