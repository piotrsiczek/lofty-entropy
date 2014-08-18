package com.spiczek.chat.frontend.composites.talks;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.spiczek.chat.frontend.composites.messages.MessageComposite;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class TalkComposite extends Composite {

    interface TalkCompositeUiBinder extends UiBinder<HTMLPanel, TalkComposite> {}
    private static TalkCompositeUiBinder uiBinder = GWT.create(TalkCompositeUiBinder.class);

    @UiField
    HTMLPanel talkPanel;

    private EventBus eventBus;
    private UserDTO user;
    private List<MessageComposite> talks = new ArrayList<MessageComposite>();
//    private Long userId;
//    private Long chatId;


    public TalkComposite(UserDTO user, EventBus eventBus) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.eventBus = eventBus;
        this.user = user;
//        this.userId = userId;
//        this.chatId = chatId;

    }

    public void createTalk(Long friendId) {
        Log.info("creating talk width " + friendId);
        MessageComposite messageComposite = new MessageComposite(eventBus, 8, 8);
        talks.add(messageComposite);
        this.talkPanel.add(messageComposite);
        //messageComposite.setLoginId(Integer.parseInt(idBox.getText()));
        //messageComposite.setReciverId(Integer.parseInt(reciverIdBox.getText()));

    }

}
