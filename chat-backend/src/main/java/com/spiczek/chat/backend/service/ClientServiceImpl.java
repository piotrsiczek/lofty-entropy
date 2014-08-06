package com.spiczek.chat.backend.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spiczek.chat.datastore.entities.DAO;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.ClientService;

public class ClientServiceImpl extends RemoteServiceServlet implements ClientService {
    public String getMessage(String msg) {

        DAO d = new DAO();
        User u = d.createUser();

      return "Send from client " + msg + " " + u.getName() + " " + u.getId() + " " + u.getSurname();
    }
}