package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
@NoArgsConstructor
public class Chat {
    @Id Long id;
    List<Key<Talk>> talks = new LinkedList<Key<Talk>>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Key<Talk>> getTalks() {
        return talks;
    }

    public void setTalk(Key<Talk> talk) {
        this.talks.add(talk);
    }

    public void setTalks(List<Key<Talk>> talks) {
        this.talks = talks;
    }
}
