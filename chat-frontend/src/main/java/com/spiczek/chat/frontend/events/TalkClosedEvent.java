package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import com.spiczek.chat.frontend.composites.messages.MessageComposite;

/**
 * @author Piotr Siczek
 */
public class TalkClosedEvent extends GenericEvent {
    MessageComposite messageComposite;

    public TalkClosedEvent(MessageComposite messageComposite) {
        this.messageComposite = messageComposite;
    }

    public MessageComposite getMessageComposite() {
        return messageComposite;
    }
}
