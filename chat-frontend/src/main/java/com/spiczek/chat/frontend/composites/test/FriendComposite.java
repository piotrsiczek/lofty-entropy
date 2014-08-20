package com.spiczek.chat.frontend.composites.test;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.spiczek.chat.frontend.events.AddListItemEvent;
import com.spiczek.chat.frontend.events.RemoveListItemEvent;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class FriendComposite extends Composite {
    interface FriendCompositeUiBinder extends UiBinder<HTMLPanel, FriendComposite> {}
    private static FriendCompositeUiBinder uiBinder = GWT.create(FriendCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<FriendComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    @UiField
    HTMLPanel scrollablePanel;

    @UiField
    HTMLPanel titlePanel;

    private EventBus eventBus;
    private List<FriendPanel> items = new ArrayList<FriendPanel>();

    public FriendComposite(EventBus eventBus) {
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
    }

    public void loadData(List<UserDTO> items) {
        Log.info("start to load data");
        for (UserDTO u : items) {
            scrollablePanel.add(new FriendPanel(eventBus, u));
        }
        Log.info("stop to load data");
    }

    public void addTitlePanel(Composite panel) {
        titlePanel.add(panel);
    }

    @EventHandler
    void onItemAdded(AddListItemEvent<FriendPanel> friend) {
        scrollablePanel.add(friend.getItem());
        this.items.add(friend.getItem());
    }

    @EventHandler
    public void onItemRemoved(RemoveListItemEvent<FriendPanel> event) {
        FriendPanel item = event.getItem();
        scrollablePanel.remove(item);
        items.remove(item);
    }
}
