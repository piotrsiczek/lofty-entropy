package com.spiczek.chat.datastore;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.spiczek.chat.datastore.entities.*;

/**
 * @author Piotr Siczek
 */
public class OfyService {
    static {
        factory().register(Friend.class);
        factory().register(User.class);
        factory().register(Talk.class);
        factory().register(Chat.class);
        factory().register(Message.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
