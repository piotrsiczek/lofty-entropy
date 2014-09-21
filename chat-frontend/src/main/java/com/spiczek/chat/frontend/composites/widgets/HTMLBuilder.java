package com.spiczek.chat.frontend.composites.widgets;

import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

/**
 * @author Piotr Siczek
 */
public class HTMLBuilder {

    public static String formatDate(Date d) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy");
        return fmt.format(d);
    }

    public static String formatTime(Date d) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("HH:mm:ss");
        return fmt.format(d);
    }
}
