package com.spiczek.chat.backend.service;

import com.spiczek.chat.backend.authentication.UserSession;
import com.spiczek.chat.datastore.Integration.DataIntegration;
import com.spiczek.chat.datastore.MessageDAO;
import com.spiczek.chat.datastore.UserDAO;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.ClientService;
import com.spiczek.chat.shared.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Piotr Siczek
 */
@Service("clientService")
public class ClientServiceImpl  implements ClientService {

    private Logger log = Logger.getLogger("ClientServiceImpl");

    @Override
    public String test(String data) {
        return "elo elo " + data;
    }


    @Override
    public UserDTO getUserDetails() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserSession u = (UserSession)auth.getPrincipal();

        //UserDetails userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new UserDTO(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(),
                u.getEmail(), u.getChat().getId(), u.getFriend().getId());
    }

    public String getMessage(String msg) {

        MessageDAO d = new MessageDAO();
        //User u = d.createUser();
        //d.getUser();
        DataIntegration.generate();
        d.test();

        return "asdf";

      //return "Send from client " + msg + " " + u.getName() + " " + u.getId() + " " + u.getSurname();
    }

    @Override
    public List<UserDTO> getFriends() {
        log.info("start to load data");
        UserDAO userDAO = new UserDAO();
        List<User> friends = userDAO.getFriends(DataIntegration.getUser());
        List<UserDTO> result = new ArrayList<UserDTO>();
        log.info("get data from datastore");
        for (User user : friends) {
            result.add(new UserDTO(user.getId(), user.getName(), user.getSurname()));
        }
        log.info("result created");
        return result;
    }

    @Override
    public List<UserDTO> getFriends(Long startId, int size) {
        log.info("start to load data");
        UserDAO userDAO = new UserDAO();
        List<User> friends = userDAO.getFriends(DataIntegration.getUser(), startId, size);
        List<UserDTO> result = new ArrayList<UserDTO>();
        log.info("get data from datastore");
        for (User user : friends) {
            result.add(new UserDTO(user.getId(), user.getName(), user.getSurname()));
        }
        log.info("result created");
        return result;
    }

    @Override
    public void generateFriends() {
        UserDAO userDAO = new UserDAO();
        User u = userDAO.createUser("a", "b", "", "", "");
        DataIntegration.generateFriend(u, 100);
    }
}