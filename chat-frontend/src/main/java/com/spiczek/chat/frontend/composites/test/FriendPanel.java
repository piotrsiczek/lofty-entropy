package com.spiczek.chat.frontend.composites.test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.spiczek.chat.frontend.composites.panels.ClickablePanel;
import com.spiczek.chat.frontend.events.RemoveListItemEvent;
import com.spiczek.chat.frontend.events.TalkOpenEvent;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class FriendPanel extends Composite {
    interface FriendPanelUiBinder extends UiBinder<HorizontalPanel, FriendPanel> {}
    private static FriendPanelUiBinder uiBinder = GWT.create(FriendPanelUiBinder.class);

    interface MyEventBinder extends EventBinder<FriendPanel> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

//    interface FriendPanelStyle extends CssResource {
//        String friendPanel();
//        String removeFriendButton();
//    }

//    @UiField
//    FriendPanelStyle style;

    @UiField ClickablePanel friendPanel;
    @UiField Button removeFriendButton;

    private EventBus eventBus;
    private UserDTO friend;


    public FriendPanel(EventBus eventBus, UserDTO friend) {
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
        this.friend = friend;
        initialize(friend);
    }

    private void initialize(UserDTO friend) {

        Button b = new Button("siemanko");

        String html = createDiv(friend.getName()) + createDiv(friend.getSurname());
        String html2 = createSpan(html);

        friendPanel.add(new HTML(html2));
    }

    @UiHandler("friendPanel")
    public void onFriendPanelCliced(ClickEvent e) {
        eventBus.fireEvent(new TalkOpenEvent(this.friend));
    }

    @UiHandler("removeFriendButton")
    public void onRemoveFriendButtonCliced(ClickEvent e) {
        eventBus.fireEvent(new RemoveListItemEvent<FriendPanel>(this));
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