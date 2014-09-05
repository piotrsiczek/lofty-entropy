package com.spiczek.chat.shared.dto;

import java.io.Serializable;

/**
 * @author Piotr Siczek
 */
public class MessageDTO implements Serializable {
    Long userId;
    private String text;

    public MessageDTO() {}

    public MessageDTO(Long userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }
}
