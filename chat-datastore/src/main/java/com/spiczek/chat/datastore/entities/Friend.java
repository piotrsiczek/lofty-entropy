package com.spiczek.chat.datastore.entities;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Entity
@Index
public class Friend {
    public static class AllFriends {}

    @Id Long id;
    @Load(AllFriends.class) List<Ref<User>> friends = new ArrayList<Ref<User>>();

    public List<User> getFriends() {
        List<User> result = new LinkedList<User>();
        for (Ref<User> u : friends) {
            result.add(u.get());
        }
        return result;
    }

    public void setFriends(List<Ref<User>> friends) {
        this.friends = friends;
    }

    public void setFriends(Ref<User> friend) {
        this.friends.add(friend);
    }


    public Friend() {}
}
