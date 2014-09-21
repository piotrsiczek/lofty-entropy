package com.spiczek.chat.frontend.composites.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
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
        HTML image = new HTML(createImage(style.messageImage(), "http://stylonica.com/wp-content/uploads/2014/04/cat_napper-wide.jpg"));
        HTML description = new HTML(createSpan(style.messageSender(), user.getName() + " " + user.getSurname()));
        HTML timee = new HTML(createSpan(style.messageTime(), HTMLBuilder.formatTime(d)));
        String topPane = createDiv("", image.getHTML() + description.getHTML() + timee.getHTML());

        HTML html = new HTML(topPane + createDiv(style.messageContent(), data));
        html.addStyleName(style.leftMessage());
        messagePane.add(html);
    }

    public void createRightMessage(MessageDTO message) {
        HTML image = new HTML(createImage(style.messageImage(), "http://stylonica.com/wp-content/uploads/2014/04/cat_napper-wide.jpg"));
        UserDTO friend = getFriend(message.getUserId());
        HTML description = new HTML(createSpan(style.messageSender(), friend.getName() + " " + friend.getSurname()));
        HTML time = new HTML(createSpan(style.messageTime(), HTMLBuilder.formatTime(message.getDate())));
        String topPane = createDiv("", description.getHTML() + time.getHTML() + image.getHTML());
        String test = "<div class='" + style.rightMessage() + "'>" + topPane + createDiv(style.messageContent(), message.getText()) + "</div>";

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

    private String createDiv(String styleName, String data) {
        return "<div class='" + styleName + "'>" + data + "</div>";
    }

    private String createSpan(String styleName, String data) {
        return "<span class='" + styleName + "'>" + data + "</span>";
    }

    private String createImage(String styleName, String url) {
        return "<img src='" + url + "' class='" + styleName + "'>";
    }

}