package com.spiczek.chat.shared.dto;

import java.io.Serializable;

/**
 * @author Piotr Siczek
 */
public class MessageDTO implements Serializable {
    private Long userId;
    private String text;
    private String time;

    public MessageDTO() {}

    public MessageDTO(Long userId, String text, String time) {
        this.userId = userId;
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public Long getUserId() {
        return userId;
    }
}
