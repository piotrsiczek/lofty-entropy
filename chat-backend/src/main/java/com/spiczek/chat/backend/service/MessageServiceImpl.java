package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.spiczek.chat.shared.MessageService;
import org.springframework.stereotype.Service;

/**
 * @author Piotr Siczek
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Override
    public String getToken(int id) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        return channelService.createChannel("someawesomename" + id);
    }

    @Override
    public void sendMessage(int fromId, int toId, String message) {
        ChannelService channelService = ChannelServiceFactory.getChannelService();
        channelService.sendMessage(new ChannelMessage("someawesomename" + toId, fromId + ";" + message));
    }
}