package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spiczek.chat.shared.MessageService;

/**
 * Created by piotr on 2014-04-27.
 */
public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {
    @Override
    public void sendMessage(int fromId, int toId, String message) {

        ChannelService channelService = ChannelServiceFactory.getChannelService();
        channelService.sendMessage(new ChannelMessage("someawesomename" + toId, fromId + ";" + message));
    }

}