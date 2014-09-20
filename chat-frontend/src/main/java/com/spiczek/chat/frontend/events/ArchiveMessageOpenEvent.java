package com.spiczek.chat.frontend.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import com.spiczek.chat.shared.dto.TalkDTO;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class ArchiveMessageOpenEvent extends GenericEvent {
    private Long talkKey;
    private List<UserDTO> dudes = new ArrayList<UserDTO>();

    public ArchiveMessageOpenEvent(TalkDTO talk) {
        this.talkKey = talk.getTalkId();
        this.dudes = talk.getDudes();
    }

    public Long getTalkKey() {
        return talkKey;
    }

    public List<UserDTO> getDudes() {
        return dudes;
    }
}
