package com.spiczek.chat.frontend.composites.toolbars;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListToolBar;
import com.spiczek.chat.frontend.events.CompositeCloseEvent;

/**
 * @author Piotr Siczek
 */
public class ArchiveTalkToolBar extends Composite implements ListToolBar {
    interface ArchiveTalkToolBarUiBinder extends UiBinder<HTMLPanel, ArchiveTalkToolBar> {}
    private static ArchiveTalkToolBarUiBinder uiBinder = GWT.create(ArchiveTalkToolBarUiBinder.class);

    @UiField
    Button closeButton;

    private ListPanel listPanel;

    public ArchiveTalkToolBar() {
        this.initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("closeButton")
    public void onCloseButtonCliced(ClickEvent e) {
        listPanel.fireEvent(new CompositeCloseEvent(listPanel));
        Log.info("cliced");
    }

    @Override
    public void setListPanel(ListPanel listPanel) {
        this.listPanel = listPanel;
    }
}
