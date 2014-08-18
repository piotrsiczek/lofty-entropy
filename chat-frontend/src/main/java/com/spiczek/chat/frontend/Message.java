package com.spiczek.chat.frontend;

/**
 * @author Piotr Siczek
 */
public class Message {
    private Long senderId;
    String userName;
    private String data;

    public Message(Long senderId, String userName, String data) {
        this.senderId = senderId;
        this.userName = userName;
        this.data = data;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getUserName() {
        return userName;
    }

    public String getData() {
        return data;
    }

}
