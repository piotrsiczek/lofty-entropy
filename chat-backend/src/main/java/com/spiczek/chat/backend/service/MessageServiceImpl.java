package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.spiczek.chat.datastore.daos.MessageDAO;
import com.spiczek.chat.shared.MessageService;
import org.springframework.stereotype.Service;

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
}