package com.spiczek.chat.backend;

import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;
import com.spiczek.chat.shared.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class DtoBuilder {
    private DtoBuilder() {}

    public static UserDTO createUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword(), user.getEmail(), user.getChat().getId(), user.getFriend().getId());
    }

    public static List<UserDTO> createUserDTOList(List<User> items) {
        List<UserDTO> result = new ArrayList<UserDTO>();
        for (User user : items) {
            result.add(createUserDTO(user));
        }
        return result;
    }

    public static TalkDTO createTalkDTO(Talk talk, List<Message> messages, List<User> users) {
        return new TalkDTO(talk.getId(), talk.getDate(), createUserDTOList(users), createMessageDTOList(messages));
    }

    public static MessageDTO createMessageDTO(Message message) {
        return new MessageDTO(message.getSender().getId(), message.getText(), message.getDate());
    }

    public static List<MessageDTO> createMessageDTOList(List<Message> items) {
        if (items == null) return null;
        List<MessageDTO> result = new ArrayList<MessageDTO>();
        for (Message message : items) {
            result.add(createMessageDTO(message));
        }
        return result;
    }
}
