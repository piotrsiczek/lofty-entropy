package com.spiczek.chat.shared.dto;

import java.io.Serializable;

/**
 * @author Piotr Siczek
 */
public class TalkDTO implements Serializable {
    private Long talkId;
    private Long dudeId;

    public TalkDTO() {}

    public TalkDTO(Long talkId, Long dudeId) {
        this.talkId = talkId;
        this.dudeId = dudeId;
    }

    public Long getTalkId() {
        return talkId;
    }

    public Long getDudeId() {
        return dudeId;
    }
}
