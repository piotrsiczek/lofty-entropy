package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
public class Talk {
    @Id Long id;
    Key<User> dude;
    List<Key<Message>> messages = new ArrayList<Key<Message>>();

    public Talk() {}

    public Talk(Key<User> dude) {
        this.dude = dude;
    }

    public Key<User> getDude() {
        return dude;
    }

    public void setDude(Key<User> dude) {
        this.dude = dude;
    }

    public void setMessage(Key<Message> messages) {
        this.messages.add(messages);
    }
}
