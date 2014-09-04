package com.spiczek.chat.frontend.composites.messages;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.spiczek.chat.frontend.composites.widgets.TextBox;
import com.spiczek.chat.frontend.events.TalkClosedEvent;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.MessageServiceAsync;
import java.util.Date;


/**
 * @author Piotr Siczek
 */
public class MessageComposite extends Composite {
    interface MessageCompositeUiBinder extends UiBinder<HTMLPanel, MessageComposite> {}
    private static MessageCompositeUiBinder ourUiBinder = GWT.create(MessageCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<MessageComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    interface MessageCompositeStyle extends CssResource {
        String rightMessage();
        String mainMessagePane();
        String leftMessage();
        String rightMessageContainer();
        String messageImage();
        String messageTime();
        String messageSender();
        String messageContent();
        @ClassName("title-panel") String titlePanel();
        @ClassName("content-pane") String contentPane();
    }

    private final MessageServiceAsync messageService = GWT.create(MessageService.class);

    @UiField MessageCompositeStyle style;
    @UiField HTMLPanel messagePane;
    @UiField TextBox messageText;
    @UiField Button sendButton;
    @UiField Button closeButton;
    @UiField HTMLPanel mainPanel;

    private EventBus eventBus;
    private Long talkKey;
    private Long loginId;
    private String userName;
    private Long receiverId;
    private String friendName;

    public MessageComposite(EventBus eventBus, Long talkKey, Long loginId, String userName, Long receiverId, String friendName) {
        this.initWidget(ourUiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.eventBus = eventBus;
        this.talkKey = talkKey;
        this.loginId = loginId;
        this.userName = userName;
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

    private void createLeftMessage(String data, String timeString) {
        HTML image = new HTML(createImage(style.messageImage(), "http://stylonica.com/wp-content/uploads/2014/04/cat_napper-wide.jpg"));
        HTML description = new HTML(createSpan(style.messageSender(), userName));
        HTML time = new HTML(createSpan(style.messageTime(), timeString));
        String topPane = createDiv("", image.getHTML() + description.getHTML() + time.getHTML());

        HTML html = new HTML(topPane + createDiv(style.messageContent(), data));
        html.addStyleName(style.leftMessage());
        messagePane.add(html);
    }

    private void createRightMessage(String data, String timeString) {
        HTML image = new HTML(createImage(style.messageImage(), "http://stylonica.com/wp-content/uploads/2014/04/cat_napper-wide.jpg"));
        HTML description = new HTML(createSpan(style.messageSender(), friendName));
        HTML time = new HTML(createSpan(style.messageTime(), timeString));
        String topPane = createDiv("", description.getHTML() + time.getHTML() + image.getHTML());

        HTML html = new HTML(data);
        html.addStyleName(style.rightMessage());

        String test = "<div class='" + style.rightMessage() + "'>" + topPane + createDiv(style.messageContent(), data) + "</div>";

        HTML row = new HTML(test);
        row.addStyleName(style.rightMessageContainer());
        messagePane.add(row);

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

    private String getCurrentTime() {
        DateTimeFormat dateFormat = DateTimeFormat.getFormat("HH:mm");
        return dateFormat.format(new Date());
    }

    public void showMessage(String data) {
        createRightMessage(data, getCurrentTime());
    }

    @UiHandler("sendButton")
    public void onSendButtonCliced(ClickEvent e) {
        final String data = messageText.getText();
        final String time = getCurrentTime();
        messageService.sendMessage(loginId, userName, receiverId, talkKey, data, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.info(caught.toString());
            }

            @Override
            public void onSuccess(Void result) {
                messageService.createMessage(data, talkKey, loginId, new AsyncCallback<Long>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Log.info(caught.toString());
                    }

                    @Override
                    public void onSuccess(Long result) {
                        Log.info("send data");
                        createLeftMessage(data, time);
                    }
                });
            }
        });
    }

    @UiHandler("closeButton")
    public void onCloseButtonCliced(ClickEvent e) {
        eventBus.fireEvent(new TalkClosedEvent(this));
        Log.info("cliced");
    }
}