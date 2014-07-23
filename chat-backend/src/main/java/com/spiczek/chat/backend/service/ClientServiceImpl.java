package com.spiczek.chat.backend.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spiczek.chat.shared.ClientService;

public class ClientServiceImpl extends RemoteServiceServlet implements ClientService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
      return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}