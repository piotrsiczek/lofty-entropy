package com.spiczek.chat.backend.service;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spiczek.chat.shared.TokenService;

/**
 * Created by piotr on 2014-04-27.
 */
public class TokenServiceImpl extends RemoteServiceServlet implements TokenService {
    @Override
    public String getToken(int id) {

        ChannelService channelService = ChannelServiceFactory.getChannelService();

        return channelService.createChannel("someawesomename" + id);
    }
}