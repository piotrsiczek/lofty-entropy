package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.spiczek.chat.datastore.daos.MessageDAO;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;
import com.spiczek.chat.shared.dto.UserDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO = new MessageDAO();

    @Override
    public String getToken(Long id) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        return channelService.createChannel("someawesomename" + id);
    }

    @Override
    public void sendMessage(Long fromId, String fromName, Long toId, Long talkId, String message) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        channelService.sendMessage(new ChannelMessage("someawesomename" + toId, fromId + ";" + fromName + ";" + talkId + ";" + message));
    }

    @Override
    public Long createTalk(Long userKey, Long usesrChatKey, Long receiverChatKey, Long receiverKey) {
        return messageDAO.createTalk(userKey, usesrChatKey, receiverChatKey, receiverKey).getId();
    }

    @Override
    public Long createMessage(String text, Date date, Long talkKey, Long senderKey) {
        return messageDAO.createMessage(text, date, talkKey, senderKey).getId();
    }

    @Override
    public List<TalkDTO> getTalks(Long userChatKey, Long receiverKey) {
        List<Talk> talks = messageDAO.getTalk(userChatKey, receiverKey);
        return createTalkDTOList(talks, messageDAO.getMessages(talks));
    }

    @Override
    public List<MessageDTO> getMessages(Long talkKey) {
        return createMessageDTOList(messageDAO.getMessages(talkKey));
    }

    private UserDTO createUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword(), user.getEmail(), user.getChat().getId(), user.getFriend().getId());
    }

    private List<UserDTO> createUserDTOList(List<User> items) {
        List<UserDTO> result = new ArrayList<UserDTO>();
        for (User user : items) {
            result.add(createUserDTO(user));
        }
        return result;
    }

    private TalkDTO createTalkDTO(Talk talk, List<Message> messages) {
        return new TalkDTO(talk.getId(), talk.getDate(), createUserDTOList(messageDAO.getTalkDudes(talk.getDudes())), createMessageDTOList(messages));
    }

    private List<TalkDTO> createTalkDTOList(List<Talk> items, List<List<Message>> messages) {
        List<TalkDTO> result = new ArrayList<TalkDTO>();
        int i = 0;
        for (Talk talk : items) {
            result.add(createTalkDTO(talk, messages.get(i)));
            i++;
        }
        return result;
    }

    private MessageDTO createMessageDTO(Message message) {
        return new MessageDTO(message.getSender().getId(), message.getText(), message.getDate());
    }

    private List<MessageDTO> createMessageDTOList(List<Message> items) {
        if (items == null) return null;
        List<MessageDTO> result = new ArrayList<MessageDTO>();
        for (Message message : items) {
            result.add(createMessageDTO(message));
        }
        return result;
    }

}