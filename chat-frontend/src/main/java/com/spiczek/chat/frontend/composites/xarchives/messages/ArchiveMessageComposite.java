package com.spiczek.chat.frontend.composites.xarchives.messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.spiczek.chat.frontend.composites.messages.MessagePanel;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
public class ArchiveMessageComposite extends Composite {
    interface ArchiveMessageUiBinder extends UiBinder<HTMLPanel, ArchiveMessageComposite> {}
    private static ArchiveMessageUiBinder uiBinder = GWT.create(ArchiveMessageUiBinder.class);

    @UiField(provided=true) MessagePanel messagePanel;

    private UserDTO user;

    public ArchiveMessageComposite(UserDTO user, List<MessageDTO> messages) {
        messagePanel = new MessagePanel(user.getLogin(), "cus");
        this.initWidget(uiBinder.createAndBindUi(this));

        this.user = user;
        initialize(messages);
    }

    private void initialize(List<MessageDTO> messages) {
        for (MessageDTO m : messages) {
            if (m.getUserId().equals(user.getId())) {
                messagePanel.createLeftMessage(m.getText(), "time");
            }
            else {
                messagePanel.createRightMessage(m.getText(), "time");
            }
        }
    }
}