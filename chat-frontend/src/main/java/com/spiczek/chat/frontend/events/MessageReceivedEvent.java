package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

/**
 * @author Piotr Siczek
 */
public class MessageReceivedEvent extends GenericEvent {
    private Long senderId;
    private Long talkId;
    private String userName;
    private String data;

    public MessageReceivedEvent(Long senderId, String userName, Long talkId, String data) {
        this.senderId = senderId;
        this.talkId = talkId;
        this.userName = userName;
        this.data = data;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getTalkId() {
        return talkId;
    }

    public String getUserName() {
        return userName;
    }

    public String getData() {
        return data;
    }
}