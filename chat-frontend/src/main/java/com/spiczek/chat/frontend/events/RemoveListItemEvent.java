package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

/**
 * @author Piotr Siczek
 */
public class RemoveListItemEvent<T> extends GenericEvent {
    private T item;

    public RemoveListItemEvent(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
