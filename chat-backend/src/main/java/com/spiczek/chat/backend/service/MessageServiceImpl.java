package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.spiczek.chat.datastore.daos.MessageDAO;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Long createTalk(Long myChatKey, Long receiverChatKey, Long receiverKey) {
        return messageDAO.createTalk(myChatKey, receiverChatKey, receiverKey).getId();
    }

    @Override
    public Long createMessage(String text, Long talkKey, Long senderKey) {
        return messageDAO.createMessage(text, talkKey, senderKey).getId();
    }

    @Override
    public List<TalkDTO> getTalks(Long userChatKey, Long receiverKey) {
        return createTalkDTOList(messageDAO.getTalk(userChatKey, receiverKey));
    }

    @Override
    public List<MessageDTO> getMessages(Long talkKey) {
        return createMessageDTOList(messageDAO.getMessages(talkKey));
    }

    private TalkDTO createTalkDTO(Talk talk) {
        return new TalkDTO(talk.getId(), talk.getDude().getId());
    }

    private List<TalkDTO> createTalkDTOList(List<Talk> items) {
        List<TalkDTO> result = new ArrayList<TalkDTO>();
        for (Talk talk : items) {
            result.add(createTalkDTO(talk));
        }
        return result;
    }

    private MessageDTO createMessageDTO(Message message) {
        return new MessageDTO(message.getSender().getId(), message.getText());
    }

    private List<MessageDTO> createMessageDTOList(List<Message> items) {
        List<MessageDTO> result = new ArrayList<MessageDTO>();
        for (Message message : items) {
            result.add(createMessageDTO(message));
        }
        return result;
    }
}