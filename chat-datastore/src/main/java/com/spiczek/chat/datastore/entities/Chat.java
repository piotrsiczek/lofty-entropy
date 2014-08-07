package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
public class Chat {
    @Id Long id;
    List<Key<Talk>> talks = new LinkedList<Key<Talk>>();

//    public Chat() {}

    public void setTalk(Key<Talk> talk) {
        this.talks.add(talk);
    }
}
