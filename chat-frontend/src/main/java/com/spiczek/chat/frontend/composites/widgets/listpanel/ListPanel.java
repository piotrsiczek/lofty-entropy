package com.spiczek.chat.frontend.composites.widgets.listpanel;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.spiczek.chat.frontend.composites.toolbars.FriendToolBar;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
public class ListPanel<T> extends Composite {
    interface FriendCompositeUiBinder extends UiBinder<HTMLPanel, ListPanel> {}
    private static FriendCompositeUiBinder uiBinder = GWT.create(FriendCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<ListPanel> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    @UiField
    HTMLPanel scrollablePanel;

    @UiField
    HTMLPanel titlePanel;

    private EventBus eventBus;
    //private List<Composite> items = new ArrayList<Composite>();
    private ListTileRenderer listRenderer;
    private UserDTO user;

    public ListPanel(EventBus eventBus, ListTileRenderer renderer, UserDTO user) {
        this.initWidget(uiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
        this.listRenderer = renderer;
        this.user = user;
        renderer.setListPanel(this);
    }

    public void addItems(List<T> items) {
        Log.info("start to load data");
        for (T t : items) {
            scrollablePanel.add(listRenderer.createTile(t));
        }
        Log.info("stop to load data");
    }

    public void setToolBar(FriendToolBar toolBar) {
        toolBar.setListPanel(this);
        titlePanel.add(toolBar);
    }

    public void addItem(Composite item) {
        scrollablePanel.add(item);
        //this.items.add(item);
    }

    public void removeItem(Composite item) {
        scrollablePanel.remove(item);
        //items.remove(item);
    }

    public UserDTO getUserDetails() {
        return user;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        eventBus.fireEvent(event);
    }
}
