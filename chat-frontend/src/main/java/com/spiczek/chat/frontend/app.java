package com.spiczek.chat.frontend;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spiczek.chat.frontend.composites.talks.TalkComposite;
import com.spiczek.chat.frontend.composites.friends.FriendPanelRenderer;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.composites.toolbars.FriendToolBar;
import com.spiczek.chat.shared.*;
import com.spiczek.chat.shared.dto.UserDTO;
import com.spiczek.chat.shared.errors.ServiceError;

import java.util.List;


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class app implements EntryPoint {

    private final ClientServiceAsync clientService = GWT.create(ClientService.class);
    private final MessageServiceAsync messageService = GWT.create(MessageService.class);
    private SimpleEventBus eventBus;

    /**
     * This is the entry point method.`
     */
    public void onModuleLoad() {
        eventBus = new SimpleEventBus();

        initWidgets();
    }

    private void initWidgets() {
        clientService.getUserDetails(new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(caught.toString());
                Window.Location.replace("/login");
            }

            @Override
            public void onSuccess(UserDTO user) {
                Log.info("user details " + user.toString());
                generateToken(user.getId());
                initFriendsWidget(user);
                initTalksWidget(user);
            }
        });
    }

    private void generateToken(Long userId) {
        messageService.getToken(userId, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(caught.toString());
            }

            @Override
            public void onSuccess(String result) {
                Log.info("success " + result);
                ChannelConnection connection = new ChannelConnection(result, eventBus);
            }
        });
    }

    private void initFriendsWidget(UserDTO user) {
        final ListPanel<UserDTO> listPanel = new ListPanel<UserDTO>(eventBus, new FriendPanelRenderer(), user);
        listPanel.setToolBar(new FriendToolBar());
        RootPanel.get("leftSlot").add(listPanel);

        clientService.getFriends(user.getFriendKey(), new AsyncCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught instanceof ServiceError) {
                    Log.error(((ServiceError)caught).getErrorMessage());
                }
                else
                    Log.error(caught.toString());
            }

            @Override
            public void onSuccess(List<UserDTO> result) {
                listPanel.addItems(result);
            }
        });

    }

    private void initTalksWidget(final UserDTO user) {
        final TalkComposite talkComposite = new TalkComposite(user, eventBus);
        RootPanel.get("centerSlot").add(talkComposite);
    }
}
