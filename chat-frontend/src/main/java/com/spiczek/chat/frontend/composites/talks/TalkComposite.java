package com.spiczek.chat.frontend.composites.talks;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.spiczek.chat.frontend.composites.messages.MessageComposite;
import com.spiczek.chat.frontend.events.MessageReceivedEvent;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class TalkComposite extends Composite {

    interface TalkCompositeUiBinder extends UiBinder<HTMLPanel, TalkComposite> {}
    private static TalkCompositeUiBinder uiBinder = GWT.create(TalkCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<TalkComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    @UiField
    HTMLPanel talkPanel;

    private EventBus eventBus;
    private UserDTO user;
    private List<MessageComposite> talks = new ArrayList<MessageComposite>();

    public TalkComposite(UserDTO user, EventBus eventBus) {
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);
        this.eventBus = eventBus;
        this.user = user;
    }

    public MessageComposite createTalk(Long friendId, String friendName) {
        Log.info("creating talk width " + friendId);
        String nameName = user.getName() + " " + user.getSurname();
        MessageComposite messageComposite = new MessageComposite(eventBus, user.getId(), nameName, friendId, friendName);
        talks.add(messageComposite);
        this.talkPanel.add(messageComposite);

        return messageComposite;
    }

    @EventHandler
    void onEmailLoaded(MessageReceivedEvent message) {
        Log.info("received message: " + message.getMessage().getSenderId() + " data: " + message.getMessage().getData());

        boolean isFound = false;
        for (MessageComposite m : talks) {
            if (m.getReceiverId().equals(message.getMessage().getSenderId())) {
                m.showMessage(message.getMessage().getData());
                isFound = true;
            }
        }

        if (!isFound) {
            MessageComposite m = createTalk(message.getMessage().getSenderId(), message.getMessage().getUserName());
            m.showMessage(message.getMessage().getData());
        }
    }
}