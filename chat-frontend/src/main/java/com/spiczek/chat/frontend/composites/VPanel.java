package com.spiczek.chat.frontend.composites;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Piotr Siczek
 */
public class VPanel extends VerticalPanel {

    public VPanel(Widget... widgets) {
        super();
        for (Widget w : widgets) {
            this.add(w);
        }
    }
}
