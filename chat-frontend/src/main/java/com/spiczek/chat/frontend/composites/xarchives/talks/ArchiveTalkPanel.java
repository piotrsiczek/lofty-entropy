package com.spiczek.chat.frontend.composites.xarchives.talks;

import com.google.gwt.core.client.GWT;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.spiczek.chat.frontend.composites.panels.ClickablePanel;
import com.spiczek.chat.frontend.composites.widgets.HTMLBuilder;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.events.ArchiveMessageOpenEvent;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.Date;
import java.util.List;


/**
 * @author Piotr Siczek
 */
public class ArchiveTalkPanel extends Composite {
    interface FriendPanelUiBinder extends UiBinder<ClickablePanel, ArchiveTalkPanel> {}
    private static FriendPanelUiBinder uiBinder = GWT.create(FriendPanelUiBinder.class);

    @UiField ClickablePanel talkPanel;

    private TalkDTO talk;
    private ListPanel listPanel;
    private boolean isEmpty;

    public ArchiveTalkPanel(ListPanel listPanel, TalkDTO talk) {
        this.initWidget(uiBinder.createAndBindUi(this));

        this.listPanel = listPanel;
        this.talk = talk;
        this.isEmpty = false;
        initialize(talk);
    }

    private void initialize(TalkDTO talk) {
        List<MessageDTO> messages = talk.getMessages();
        if (messages != null) {
            for (MessageDTO m : messages) {
                UserDTO friend = getFriend(m.getUserId());
                Date date = m.getDate();
                String data = friend.getName() + " " + friend.getSurname()
                              + " " + HTMLBuilder.formatDate(date)
                              + " " + HTMLBuilder.formatTime(date);
                talkPanel.add(new HTML(data));
                talkPanel.add(new HTML(m.getText()));

            }
        }
        else {
            isEmpty = true;
            UserDTO friend = talk.getDudes().get(0);
            //TODO multi users support
            String data = friend.getName() + " " + friend.getSurname()
                    + " " + talk.getDate();
            //TODO date as date not string
            talkPanel.add(new HTML(data));
            talkPanel.add(new HTML("Brak wiadomości."));
        }
    }

    private UserDTO getFriend(Long friendId) {
        for (UserDTO u : talk.getDudes()) {
            if (u.getId().equals(friendId)) {
                return u;
            }
        }
        return null;
    }

    @UiHandler("talkPanel")
    public void onTalkPanelCliced(ClickEvent e) {
        if (!isEmpty) {
            Log.info("fired");
            listPanel.fireEvent(new ArchiveMessageOpenEvent(talk));
        }
    }

}
