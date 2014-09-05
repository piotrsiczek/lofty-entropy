package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class ArchiveTalkOpenEvent extends GenericEvent {
    UserDTO friend;

    public ArchiveTalkOpenEvent(UserDTO friend) {
        this.friend = friend;
    }

    public UserDTO getFriend() {
        return friend;
    }
}
