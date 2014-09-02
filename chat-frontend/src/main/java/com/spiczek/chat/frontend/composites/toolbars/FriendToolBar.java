package com.spiczek.chat.frontend.composites.toolbars;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.spiczek.chat.frontend.composites.friends.FriendPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListToolBar;
import com.spiczek.chat.frontend.composites.widgets.TextBox;
import com.spiczek.chat.shared.ClientService;
import com.spiczek.chat.shared.ClientServiceAsync;
import com.spiczek.chat.shared.dto.UserDTO;
import com.spiczek.chat.shared.errors.ServiceError;

/**
 * @author Piotr Siczek
 */
public class FriendToolBar extends Composite implements ListToolBar {
    interface FriendToolBarUiBinder extends UiBinder<HTMLPanel, FriendToolBar> {}
    private static FriendToolBarUiBinder uiBinder = GWT.create(FriendToolBarUiBinder.class);

    private final ClientServiceAsync clientService = GWT.create(ClientService.class);

    @UiField Button openAddPanelButton;
    @UiField Button addFriendButton;
    @UiField HTMLPanel addFriendPanel;
    @UiField TextBox friendLoginText;
    @UiField Label errorLabel;

    private ListPanel listPanel;

    public FriendToolBar() {
        this.initWidget(uiBinder.createAndBindUi(this));
        initialize();
    }

    private void initialize() {
        addFriendPanel.setVisible(false);
        friendLoginText.setEnterButton(addFriendButton);
    }

    @UiHandler("openAddPanelButton")
    public void onAddPanelButtonCliced(ClickEvent e) {
        if (addFriendPanel.isVisible()) {
            openAddPanelButton.setText("+");
            addFriendPanel.setVisible(false);
            friendLoginText.setText("");
            errorLabel.setText("");
        }
        else {
            openAddPanelButton.setText("Anuluj");
            addFriendPanel.setVisible(true);
        }
    }

    @UiHandler("addFriendButton")
    public void onAddFriendButtonCliced(ClickEvent e) {
        clientService.addFriend(listPanel.getUserDetails().getFriendKey(), friendLoginText.getText(), new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ServiceError) {
                    String error = ((ServiceError)caught).getErrorMessage();
                    errorLabel.setText(error);
                    Log.error(error);
                }
                else
                    Log.error(caught.toString());
            }

            @Override
            public void onSuccess(UserDTO result) {
                listPanel.addItem(new FriendPanel(listPanel, result));
            }
        });
    }

    @Override
    public void setListPanel(ListPanel listPanel) {
        this.listPanel = listPanel;
    }
}