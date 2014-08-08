package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
@NoArgsConstructor
public class Talk {
    @Id Long id;
    Key<User> dude;
    List<Key<Message>> messages = new ArrayList<Key<Message>>();


    public Talk(Key<User> dude) {
        this.dude = dude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<User> getDude() {
        return dude;
    }

    public void setDude(Key<User> dude) {
        this.dude = dude;
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
