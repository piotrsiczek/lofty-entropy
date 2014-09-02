package com.spiczek.chat.frontend.composites.friends;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.spiczek.chat.frontend.composites.panels.ClickablePanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.events.TalkOpenEvent;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class FriendPanel extends Composite {
    interface FriendPanelUiBinder extends UiBinder<HorizontalPanel, FriendPanel> {}
    private static FriendPanelUiBinder uiBinder = GWT.create(FriendPanelUiBinder.class);

//    interface FriendPanelStyle extends CssResource {
//        String friendPanel();
//        String removeFriendButton();
//    }

//    @UiField
//    FriendPanelStyle style;
    @UiField ClickablePanel friendPanel;
    @UiField Button removeFriendButton;

    private UserDTO friend;
    private ListPanel listPanel;

    public FriendPanel(ListPanel listPanel, UserDTO friend) {
        this.initWidget(uiBinder.createAndBindUi(this));

        this.listPanel = listPanel;
        this.friend = friend;
        initialize(friend);
    }

    private void initialize(UserDTO friend) {
        String html = createDiv(friend.getName()) + createDiv(friend.getSurname());
        String html2 = createSpan(html);

        friendPanel.add(new HTML(html2));
    }

    @UiHandler("friendPanel")
    public void onFriendPanelCliced(ClickEvent e) {
        Log.info("fired");
        listPanel.fireEvent(new TalkOpenEvent(this.friend));
    }

    @UiHandler("removeFriendButton")
    public void onRemoveFriendButtonCliced(ClickEvent e) {
        Log.info("from remove");
        listPanel.removeItem(this);
    }

    private String createDiv(String styleName, String data) {
        return "<div class='" + styleName + "'>" + data + "</div>";
    }

    private String createDiv(String data) {
        return "<div>" + data + "</div>";
    }

    private String createSpan(String data) {
        return "<span>" + data + "</span>";
    }
}