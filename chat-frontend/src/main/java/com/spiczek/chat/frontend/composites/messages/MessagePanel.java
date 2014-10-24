package com.spiczek.chat.frontend.composites.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.spiczek.chat.frontend.composites.widgets.HTMLBuilder;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.UserDTO;
import java.util.Date;
import java.util.List;


/**
 * @author Piotr Siczek
 */
public class MessagePanel extends Composite {
    interface MessagePanelUiBinder extends UiBinder<HTMLPanel, MessagePanel> {}
    private static MessagePanelUiBinder ourUiBinder = GWT.create(MessagePanelUiBinder.class);

    interface MessagePanelStyle extends CssResource {
        String rightMessage();
        String leftMessage();
        String rightMessageContainer();
        String mainContent();
        String messageImage();
        String messageTime();
        String messageSender();
        String messageContent();
    }

    @UiField MessagePanelStyle style;
    @UiField HTMLPanel messagePane;

    private UserDTO user;
    private List<UserDTO> dudes;

    public MessagePanel(UserDTO user, List<UserDTO> dudes) {
        this.initWidget(ourUiBinder.createAndBindUi(this));

        this.user = user;
        this.dudes = dudes;
    }

    public void createLeftMessage(String data, Date d) {
        HTML description = new HTML(HTMLBuilder.createSpan(style.messageSender(), user.getName() + " " + user.getSurname()));
        HTML timee = new HTML(HTMLBuilder.createSpan(style.messageTime(), HTMLBuilder.formatTime(d)));
        String topPane = HTMLBuilder.createDiv("", description.getHTML() + timee.getHTML());

        HTML html = new HTML(topPane + HTMLBuilder.createDiv(style.messageContent(), data));
        html.addStyleName(style.leftMessage());
        messagePane.add(html);
    }

    public void createRightMessage(MessageDTO message) {
        UserDTO friend = getFriend(message.getUserId());
        HTML description = new HTML(HTMLBuilder.createSpan(style.messageSender(), friend.getName() + " " + friend.getSurname()));
        HTML time = new HTML(HTMLBuilder.createSpan(style.messageTime(), HTMLBuilder.formatTime(message.getDate())));
        String topPane = HTMLBuilder.createDiv("", description.getHTML() + time.getHTML());
        String test = "<div class='" + style.rightMessage() + "'>" + topPane + HTMLBuilder.createDiv(style.messageContent(), message.getText()) + "</div>";

        HTML row = new HTML(test);
        row.addStyleName(style.rightMessageContainer());
        messagePane.add(row);
    }

    private UserDTO getFriend(Long friendId) {
        for (UserDTO u : dudes) {
            if (u.getId().equals(friendId)) {
                return u;
            }
        }
        return null;
    }

}