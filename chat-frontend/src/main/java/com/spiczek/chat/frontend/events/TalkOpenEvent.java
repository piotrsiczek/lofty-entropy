package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class TalkOpenEvent extends GenericEvent {
    UserDTO friend;

    public TalkOpenEvent(UserDTO friend) {
        this.friend = friend;
    }

    public UserDTO getFriend() {
        return friend;
    }
}
