package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import com.spiczek.chat.frontend.Message;

/**
 * @author Piotr Siczek
 */
public class MessageReceivedEvent extends GenericEvent {
    private Message message;

    public MessageReceivedEvent(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}