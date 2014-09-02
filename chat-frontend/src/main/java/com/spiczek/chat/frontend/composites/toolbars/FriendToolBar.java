package com.spiczek.chat.frontend.composites.toolbars;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.spiczek.chat.frontend.composites.friends.FriendPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListToolBar;
import com.spiczek.chat.frontend.composites.widgets.TextBox;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class FriendToolBar extends Composite implements ListToolBar {
    interface FriendToolBarUiBinder extends UiBinder<HTMLPanel, FriendToolBar> {}
    private static FriendToolBarUiBinder uiBinder = GWT.create(FriendToolBarUiBinder.class);

    @UiField Button openAddPanelButton;
    @UiField Button addFriendButton;
    @UiField HTMLPanel addFriendPanel;
    @UiField TextBox friendEmailText;

    private ListPanel listPanel;

    public FriendToolBar() {
        this.initWidget(uiBinder.createAndBindUi(this));
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
        UserDTO u = new UserDTO(new Long(1), "asdf", "asdf");


        listPanel.addItem(new FriendPanel(listPanel, u));
    }

    @Override
    public void setListPanel(ListPanel listPanel) {
        this.listPanel = listPanel;
    }
}