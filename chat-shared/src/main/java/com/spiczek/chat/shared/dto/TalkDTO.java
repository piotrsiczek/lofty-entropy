package com.spiczek.chat.shared.dto;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class TalkDTO implements Serializable {
    private Long talkId;
    private Date date;
    private List<UserDTO> dudes = new ArrayList<UserDTO>();
    private List<MessageDTO> messages;

    public TalkDTO() {}

//    public TalkDTO(Long talkId, List<UserDTO> dudes) {
//        this.talkId = talkId;
//        this.dudes = dudes;
//    }

    public TalkDTO(Long talkId, Date date, List<UserDTO> dudes, List<MessageDTO> messages) {
        this.talkId = talkId;
        this.date = date;
        this.dudes = dudes;
        this.messages = messages;
    }

    public Long getTalkId() {
        return talkId;
    }

    public String getDate() {
        if (date != null) {
            DateTimeFormat fmt = DateTimeFormat.getFormat("dd.MM.yyyy");
            return fmt.format(date);
        }
        else {
            return "";
        }
    }

    public List<UserDTO> getDudes() {
        return dudes;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }
}
