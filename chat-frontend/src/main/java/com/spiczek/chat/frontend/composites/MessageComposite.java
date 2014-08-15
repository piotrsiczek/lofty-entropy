package com.spiczek.chat.frontend.composites;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;
import com.spiczek.chat.frontend.events.MessageReceivedEvent;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.MessageServiceAsync;

import java.util.logging.Logger;


/**
 * Created by piotr on 2014-05-11.
 */
public class MessageComposite extends Composite {
    //ui binder setup
    interface MessageCompositeUiBinder extends UiBinder<HTMLPanel, MessageComposite> {}
    private static MessageCompositeUiBinder ourUiBinder = GWT.create(MessageCompositeUiBinder.class);

    interface MyEventBinder extends EventBinder<MessageComposite> {}
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

    interface MessageCompositeStyle extends CssResource {
        String rightMessage();
        String mainMessagePane();
        String leftMessage();
        String rightMessageContainer();
        String messageTitlePane();
        String messageImage();
        String messageTime();
        String messageSender();
        String messageContent();
    }
    private final MessageServiceAsync messageService = GWT.create(MessageService.class);
    @UiField
    MessageCompositeStyle style;
    @UiField
    HTMLPanel messagePane;
    @UiField
    TextBox messageText;
    @UiField
    Button sendButton;

    private int loginId;
    private int reciverId;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciverId) {
        this.reciverId = reciverId;
    }

    public MessageComposite(EventBus eventBus, int loginId, int reciverId) {
        this.initWidget(ourUiBinder.createAndBindUi(this));
        eventBinder.bindEventHandlers(this, eventBus);

        this.loginId = loginId;
        this.reciverId = reciverId;
        //createMessage();
    }

    public void createMessage() {
        createLeftMessage("Mark McMoris", "left test");
        createRightMessage("Mark McMoris", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat");
        createRightMessage("Mark McMoris", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat");
        createLeftMessage("Mark McMoris", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat");
        createRightMessage("Mark McMoris", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat");
    }

    private void createLeftMessage(String userName, String data) {
        HTML image = new HTML(createImage(style.messageImage(), "http://stylonica.com/wp-content/uploads/2014/04/cat_napper-wide.jpg"));
        HTML description = new HTML(createSpan(style.messageSender(), userName));
        HTML time = new HTML(createSpan(style.messageTime(), "16.53"));
        String topPane = createDiv("", image.getHTML() + description.getHTML() + time.getHTML());

        HTML html = new HTML();
        html.addStyleName(style.leftMessage());
        html.setHTML(topPane + createDiv(style.messageContent(), data));
        messagePane.add(html);
    }

    private void createRightMessage(String userName, String data) {

        HTML image = new HTML(createImage(style.messageImage(), "http://stylonica.com/wp-content/uploads/2014/04/cat_napper-wide.jpg"));
        HTML description = new HTML(createSpan(style.messageSender(), userName));
        HTML time = new HTML(createSpan(style.messageTime(), "16.53"));
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

    @UiHandler("sendButton")
    public void onSendButtonCliced(ClickEvent e) {
        final String data = messageText.getText();
        messageService.sendMessage(loginId, reciverId, data, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("error");
            }

            @Override
            public void onSuccess(Void result) {
                //Window.alert("success");
                Log.info("success");
                createLeftMessage("User id: " + loginId, data);
            }
        });
    }

    @EventHandler
    void onEmailLoaded(MessageReceivedEvent message) {

        createRightMessage("User id: " + message.getMessage().getSenderId(), message.getMessage().getData());
        //Window.alert("recived message: " + message.getMessage().getSenderId() + " data: " + message.getMessage().getData());
    }

}