package com.spiczek.chat.backend.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spiczek.chat.shared.forntendService;

@SuppressWarnings("serial")
public class forntendServiceImpl extends RemoteServiceServlet implements forntendService {
    // Implementation of sample interface method
    public String getMessage(String msg) {

        return "elo elo";
        //return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}