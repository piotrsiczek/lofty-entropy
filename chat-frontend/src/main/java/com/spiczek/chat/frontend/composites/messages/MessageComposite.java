package com.spiczek.chat.frontend.composites.messages;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.spiczek.chat.frontend.composites.widgets.TextBox;
import com.spiczek.chat.frontend.events.CompositeCloseEvent;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.MessageServiceAsync;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Piotr Siczek
 */
public class MessageComposite extends Composite {
    interface MessageCompositeUiBinder extends UiBinder<HTMLPanel, MessageComposite> {}
    private static MessageCompositeUiBinder ourUiBinder = GWT.create(MessageCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<MessageComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    private final MessageServiceAsync messageService = GWT.create(MessageService.class);

    @UiField(provided=true) MessagePanel messagePanel;
    @UiField TextBox messageText;
    @UiField Button sendButton;
    @UiField Button closeButton;
    @UiField HTMLPanel mainPanel;

    private EventBus eventBus;
    private Long talkKey;
    private UserDTO user;
    private List<UserDTO> dudes = new ArrayList<UserDTO>();
    private Long receiverId;

    public MessageComposite(EventBus eventBus, Long talkKey, UserDTO user, List<UserDTO> dudes) {
        messagePanel = new MessagePanel(user, dudes);
        this.initWidget(ourUiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
        this.talkKey = talkKey;
        this.user = user;
        this.dudes = dudes;
        this.receiverId = this.dudes.get(0).getId();

        messageText.setEnterButton(sendButton);
    }

    public Long getTalkKey() {
        return talkKey;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void showMessage(Long userId, String data) {
        messagePanel.createRightMessage(new MessageDTO(userId, data, new Date()));
    }

    @UiHandler("sendButton")
    public void onSendButtonCliced(ClickEvent e) {
        final String data = messageText.getText();
        final Date date = new Date();
        String userName = user.getName() + " " + user.getSurname();
        messageService.sendMessage(user.getId(), userName, receiverId, talkKey, data, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.info(caught.toString());
            }

            @Override
            public void onSuccess(Void result) {
                messageService.createMessage(data, date, talkKey, user.getId(), new AsyncCallback<Long>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Log.info(caught.toString());
                    }

                    @Override
                    public void onSuccess(Long result) {
                        Log.info("send data");
                        messagePanel.createLeftMessage(data, date);
                    }
                });
            }
        });
    }

    @UiHandler("closeButton")
    public void onCloseButtonCliced(ClickEvent e) {
        eventBus.fireEvent(new CompositeCloseEvent(this));
        Log.info("cliced");
    }
}