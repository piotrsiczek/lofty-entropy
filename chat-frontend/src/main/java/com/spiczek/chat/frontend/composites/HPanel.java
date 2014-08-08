package com.spiczek.chat.frontend.composites;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Piotr Siczek
 */
public class HPanel extends HorizontalPanel {

    public HPanel(Widget... widgets) {
        super();
        for (Widget w : widgets) {
            this.add(w);
        }
    }
}
