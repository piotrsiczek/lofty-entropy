package com.spiczek.chat.backend.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spiczek.chat.datastore.MessageDAO;
import com.spiczek.chat.datastore.UserDAO;
import com.spiczek.chat.shared.ClientService;

public class ClientServiceImpl extends RemoteServiceServlet implements ClientService {
    public String getMessage(String msg) {

        MessageDAO d = new MessageDAO();
        //User u = d.createUser();

        //d.getUser();
        d.test();

        return "asdf";

      //return "Send from client " + msg + " " + u.getName() + " " + u.getId() + " " + u.getSurname();
    }
}