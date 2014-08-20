package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

/**
 * @author Piotr Siczek
 */
public class AddListItemEvent<T> extends GenericEvent {
    private T item;

    public AddListItemEvent(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
