package com.spiczek.chat.frontend.composites;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * @author Piotr Siczek
 */
public class FriendCell extends AbstractCell<UserDTO> {
    private Long id;

    @Override
    public void render(Context context, UserDTO value, SafeHtmlBuilder sb) {
        if (value == null) {
            return;
        }
        sb.appendHtmlConstant("<table><tr>");
        // Add the name and address.
        sb.appendHtmlConstant("<td style='font-size:95%;'>");
        sb.appendEscaped(value.getName());
        sb.appendHtmlConstant("</td></tr><tr><td>");
        sb.appendEscaped(value.getSurname());
        sb.appendHtmlConstant("</td></tr></table>");

    }
}
