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

    public static String createDiv(String styleName, String data) {
        return "<div class='" + styleName + "'>" + data + "</div>";
    }

    public static String createSpan(String styleName, String data) {
        return "<span class='" + styleName + "'>" + data + "</span>";
    }

    public static String createImage(String styleName, String url) {
        return "<img src='" + url + "' class='" + styleName + "'>";
    }

    public static String createDiv(String data) {
        return "<div>" + data + "</div>";
    }

    public static String createSpan(String data) {
        return "<span>" + data + "</span>";
    }





}
