package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.spiczek.chat.backend.DtoBuilder;
import com.spiczek.chat.datastore.daos.MessageDAO;
import com.spiczek.chat.datastore.entities.Message;
import com.spiczek.chat.datastore.entities.Talk;
import com.spiczek.chat.shared.MessageService;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Piotr Siczek
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    private static final String CHANNEL_SECURE_NAME = "someawesomename";
    private ChannelService channelService = ChannelServiceFactory.getChannelService();
    private MessageDAO messageDAO;

    @Override
    public String getToken(Long id) {
        return channelService.createChannel(CHANNEL_SECURE_NAME + id);
    }

    @Override
    public void sendMessage(Long fromId, String fromName, Long toId, Long talkId, String message) {
        channelService.sendMessage(new ChannelMessage(CHANNEL_SECURE_NAME + toId, fromId + ";" + fromName + ";" + talkId + ";" + message));
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
        return DtoBuilder.createMessageDTOList(messageDAO.getMessages(talkKey));
    }

    private List<TalkDTO> createTalkDTOList(List<Talk> items, List<List<Message>> messages) {
        List<TalkDTO> result = new ArrayList<TalkDTO>();
        int i = 0;
        for (Talk talk : items) {
            result.add(DtoBuilder.createTalkDTO(talk, messages.get(i), messageDAO.getTalkDudes(talk.getDudes())));
            i++;
        }
        return result;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }
}