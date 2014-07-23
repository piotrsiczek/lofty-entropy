package com.spiczek.chat.frontend;

/**
 * Created by piotr on 2014-05-11.
 */
public class Message {
    private int senderId;
    private String data;

    public Message(int senderId, String data) {
        this.senderId = senderId;
        this.data = data;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
