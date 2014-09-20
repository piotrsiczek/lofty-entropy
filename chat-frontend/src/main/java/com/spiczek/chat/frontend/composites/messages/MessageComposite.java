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
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.Date;


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
    private Long receiverId;
    private String friendName;

    public MessageComposite(EventBus eventBus, Long talkKey, UserDTO user, Long receiverId, String friendName) {
        messagePanel = new MessagePanel(user, friendName);
        this.initWidget(ourUiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
        this.talkKey = talkKey;
        this.user = user;
        this.receiverId = receiverId;
        this.friendName = friendName;

        messageText.setEnterButton(sendButton);
    }

    public Long getTalkKey() {
        return talkKey;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    private String getCurrentTime() {
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("HH:mm");
        return dateFormat.format(new Date());
    }

    public void showMessage(String data) {
        messagePanel.createRightMessage(data, getCurrentTime());
    }

    @UiHandler("sendButton")
    public void onSendButtonCliced(ClickEvent e) {
        final String data = messageText.getText();
        final String time = getCurrentTime();
        String userName = user.getName() + " " + user.getSurname();
        messageService.sendMessage(user.getId(), userName, receiverId, talkKey, data, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.info(caught.toString());
            }

            @Override
            public void onSuccess(Void result) {
                messageService.createMessage(data, time, talkKey, user.getId(), new AsyncCallback<Long>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Log.info(caught.toString());
                    }

                    @Override
                    public void onSuccess(Long result) {
                        Log.info("send data");
                        messagePanel.createLeftMessage(data, time);
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