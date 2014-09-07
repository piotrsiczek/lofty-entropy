package com.spiczek.chat.frontend.composites.xarchives.messages;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.spiczek.chat.frontend.composites.messages.MessagePanel;
import com.spiczek.chat.frontend.events.CompositeCloseEvent;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
public class ArchiveMessageComposite extends Composite {
    interface ArchiveMessageUiBinder extends UiBinder<HTMLPanel, ArchiveMessageComposite> {}
    private static ArchiveMessageUiBinder uiBinder = GWT.create(ArchiveMessageUiBinder.class);

    interface MyEventBinder extends EventBinder<ArchiveMessageComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    @UiField(provided=true) MessagePanel messagePanel;
    @UiField Button closeButton;

    private EventBus eventBus;
    private UserDTO user;

    public ArchiveMessageComposite(EventBus eventBus, UserDTO user, List<MessageDTO> messages) {
        messagePanel = new MessagePanel(user.getLogin(), "cus");
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);
        this.eventBus = eventBus;

        this.user = user;
        initialize(messages);
    }

    private void initialize(List<MessageDTO> messages) {
        for (MessageDTO m : messages) {
            if (m.getUserId().equals(user.getId())) {
                messagePanel.createLeftMessage(m.getText(), "time");
            }
            else {
                messagePanel.createRightMessage(m.getText(), "time");
            }
        }
    }

    @UiHandler("closeButton")
    public void onCloseButtonCliced(ClickEvent e) {
        Log.info("cliced");
        eventBus.fireEvent(new CompositeCloseEvent(this));
    }
}