package com.spiczek.chat.frontend.composites.toolbars;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.spiczek.chat.frontend.composites.widgets.TextBox;
import com.spiczek.chat.frontend.events.AddListItemEvent;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class FriendToolBar extends Composite {
    interface FriendToolBarUiBinder extends UiBinder<HTMLPanel, FriendToolBar> {}
    private static FriendToolBarUiBinder uiBinder = GWT.create(FriendToolBarUiBinder.class);

    interface MyEventBinder extends EventBinder<FriendToolBar> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    @UiField Button openAddPanelButton;
    @UiField Button addFriendButton;
    @UiField HTMLPanel addFriendPanel;
    @UiField TextBox friendEmailText;

    private EventBus eventBus;

    public FriendToolBar(EventBus eventBus) {
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
        initialize();
    }

    private void initialize() {
        addFriendPanel.setVisible(false);
        friendEmailText.setEnterButton(addFriendButton);
    }

    @UiHandler("openAddPanelButton")
    public void onAddPanelButtonCliced(ClickEvent e) {
        if (addFriendPanel.isVisible()) {
            openAddPanelButton.setText("+");
            addFriendPanel.setVisible(false);
        }
        else {
            openAddPanelButton.setText("Anuluj");
            addFriendPanel.setVisible(true);
        }
    }

    @UiHandler("addFriendButton")
    public void onAddFriendButtonCliced(ClickEvent e) {
        //addFriend
        UserDTO u = new UserDTO(new Long(1), "asdf", "asdf");
        eventBus.fireEvent(new AddListItemEvent<UserDTO>(u));
    }
}