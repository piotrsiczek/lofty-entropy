package com.spiczek.chat.shared.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Piotr Siczek
 */
public class MessageDTO implements Serializable {
    private Long userId;
    private String text;
    private Date date;

    public MessageDTO() {}

    public MessageDTO(Long userId, String text, Date time) {
        this.userId = userId;
        this.text = text;
        this.date = time;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Long getUserId() {
        return userId;
    }
}
