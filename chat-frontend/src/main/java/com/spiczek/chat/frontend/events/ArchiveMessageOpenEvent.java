package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

/**
 * @author Piotr Siczek
 */
public class ArchiveMessageOpenEvent extends GenericEvent {
    public Long getTalkKey;

    public ArchiveMessageOpenEvent(Long getTalkKey) {
        this.getTalkKey = getTalkKey;
    }

    public Long getGetTalkKey() {
        return getTalkKey;
    }
}
