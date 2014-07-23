package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import com.spiczek.chat.frontend.Message;

/**
 * Created by piotr on 2014-05-11.
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