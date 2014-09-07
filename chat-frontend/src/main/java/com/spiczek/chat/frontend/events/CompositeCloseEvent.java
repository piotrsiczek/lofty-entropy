package com.spiczek.chat.frontend.events;

import com.google.gwt.user.client.ui.Composite;
import com.google.web.bindery.event.shared.binder.GenericEvent;

/**
 * @author Piotr Siczek
 */
public class CompositeCloseEvent extends GenericEvent {
    Composite composite;

    public CompositeCloseEvent(Composite composite) {
        this.composite = composite;
    }

    public Composite getComposite() {
        return composite;
    }
}
