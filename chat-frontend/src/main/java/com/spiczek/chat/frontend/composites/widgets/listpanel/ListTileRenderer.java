package com.spiczek.chat.frontend.composites.widgets.listpanel;

import com.google.gwt.user.client.ui.Composite;

/**
 * @author Piotr Siczek
 */
public interface ListTileRenderer extends ListToolBar {
    public Composite createTile(Object o);
}
