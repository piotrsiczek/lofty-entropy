package com.spiczek.chat.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class TalkDTO implements Serializable {
    private Long talkId;
    private List<UserDTO> dudes = new ArrayList<UserDTO>();

    public TalkDTO() {}

    public TalkDTO(Long talkId, List<UserDTO> dudes) {
        this.talkId = talkId;
        this.dudes = dudes;
    }

    public Long getTalkId() {
        return talkId;
    }

    public List<UserDTO> getDudes() {
        return dudes;
    }
}
