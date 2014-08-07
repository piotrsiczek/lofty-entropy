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
public class Friend {
    @Id Long id;
    List<Key<User>> friends = new ArrayList<Key<User>>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Key<User>> getFriends() {
        return friends;
    }

    public void setFriends(List<Key<User>> friends) {
        this.friends = friends;
    }

    public void setFriends(Key<User> friend) {
        this.friends.add(friend);
    }
}
