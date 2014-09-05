package com.spiczek.chat.frontend.composites.xarchives.talks;

import com.google.gwt.core.client.GWT;
import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.spiczek.chat.frontend.composites.panels.ClickablePanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.events.ArchiveMessageOpenEvent;
import com.spiczek.chat.shared.dto.TalkDTO;


/**
 * @author Piotr Siczek
 */
public class ArchiveTalkPanel extends Composite {
    interface FriendPanelUiBinder extends UiBinder<HTMLPanel, ArchiveTalkPanel> {}
    private static FriendPanelUiBinder uiBinder = GWT.create(FriendPanelUiBinder.class);

    @UiField ClickablePanel talkPanel;

    private TalkDTO talk;
    private ListPanel listPanel;

    public ArchiveTalkPanel(ListPanel listPanel, TalkDTO talk) {
        this.initWidget(uiBinder.createAndBindUi(this));

        this.listPanel = listPanel;
        this.talk = talk;
        initialize(talk);
    }

    private void initialize(TalkDTO talk) {
//        String html = createDiv(friend.getName()) + createDiv(friend.getSurname());
//        String html2 = createSpan(html);

        talkPanel.add(new HTML(talk.getTalkId().toString()));
    }

    @UiHandler("talkPanel")
    public void onTalkPanelCliced(ClickEvent e) {
        Log.info("fired");
        listPanel.fireEvent(new ArchiveMessageOpenEvent(talk.getTalkId()));
    }

}
