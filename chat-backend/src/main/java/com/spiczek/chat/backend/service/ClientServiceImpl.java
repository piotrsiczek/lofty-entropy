package com.spiczek.chat.backend.service;

import com.spiczek.chat.backend.authentication.UserSession;
import com.spiczek.chat.datastore.Integration.DataIntegration;
import com.spiczek.chat.datastore.MessageDAO;
import com.spiczek.chat.datastore.UserDAO;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.ClientService;
import com.spiczek.chat.shared.dto.UserDTO;
import com.spiczek.chat.shared.errors.ServiceError;
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
    private UserDAO userDAO = new UserDAO();

    @Override
    public UserDTO getUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserSession u = (UserSession)auth.getPrincipal();

        return new UserDTO(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(),
                u.getEmail(), u.getChat().getId(), u.getFriend().getId());
    }

    @Override
    public List<UserDTO> getFriends(Long friendId) throws ServiceError {
        List<User> friends = userDAO.getFriends(friendId);
        if (friends == null) throw new ServiceError(ErrorCodes.NO_FRIENDS_ERROR);

        log.info("start getting friends");
        List<UserDTO> result = createUserDTOList(friends);
        log.info("end getting friends");
        return result;
    }

    @Override
    public UserDTO addFriend(Long friendEntityKey, String login) throws ServiceError {
        User friend = userDAO.findUser(login);
        if (friend == null) {
            throw new ServiceError(ErrorCodes.NO_USER_LOGIN);
        }

        if (userDAO.createFriend(friendEntityKey, friend) == null) {
            throw new ServiceError(ErrorCodes.USER_IS_FRIEND_ALREADY);
        }

        return createUserDTO(friend);
    }

    private UserDTO createUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword(), user.getEmail(), user.getChat().getId(), user.getFriend().getId());
    }

    private List<UserDTO> createUserDTOList(List<User> items) {
        List<UserDTO> result = new ArrayList<UserDTO>();
        for (User user : items) {
            result.add(createUserDTO(user));
        }
        return result;
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

//    @Override
//    public List<UserDTO> getFriends() {
//        log.info("start to load data");
//        UserDAO userDAO = new UserDAO();
//        List<User> friends = userDAO.getFriends(DataIntegration.getUser());
//        List<UserDTO> result = new ArrayList<UserDTO>();
//        log.info("get data from datastore");
//        for (User user : friends) {
//            result.add(new UserDTO(user.getId(), user.getName(), user.getSurname()));
//        }
//        log.info("result created");
//        return result;
//    }
//
//    @Override
//    public List<UserDTO> getFriends(Long startId, int size) {
//        log.info("start to load data");
//        UserDAO userDAO = new UserDAO();
//        List<User> friends = userDAO.getFriends(DataIntegration.getUser(), startId, size);
//        List<UserDTO> result = new ArrayList<UserDTO>();
//        log.info("get data from datastore");
//        for (User user : friends) {
//            result.add(new UserDTO(user.getId(), user.getName(), user.getSurname()));
//        }
//        log.info("result created");
//        return result;
//    }



    @Override
    public void generateFriends() {
        UserDAO userDAO = new UserDAO();
        User u = userDAO.createUser("a", "b", "", "", "");
        DataIntegration.generateFriend(u, 100);
    }

    @Override
    public String test(String data) {
        return "elo elo " + data;
    }

}