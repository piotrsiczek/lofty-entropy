package com.spiczek.chat.frontend.composites.xarchives.talks;

import com.google.gwt.user.client.ui.Composite;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListPanel;
import com.spiczek.chat.frontend.composites.widgets.listpanel.ListTileRenderer;
import com.spiczek.chat.shared.dto.TalkDTO;

/**
 * @author Piotr Siczek
 */
public class ArchiveTalkRenderer implements ListTileRenderer {
    private ListPanel listPanel;

    @Override
    public void setListPanel(ListPanel listPanel) {
        this.listPanel = listPanel;
    }

    @Override
    public Composite createTile(Object o) {
        return new ArchiveTalkPanel(listPanel, (TalkDTO)o);
    }
}
