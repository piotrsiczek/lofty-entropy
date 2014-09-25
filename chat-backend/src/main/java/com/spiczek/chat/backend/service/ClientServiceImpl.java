package com.spiczek.chat.backend.service;

import com.spiczek.chat.backend.DtoBuilder;
import com.spiczek.chat.backend.ErrorCodes;
import com.spiczek.chat.backend.authentication.UserSession;
import com.spiczek.chat.datastore.daos.UserDAO;
import com.spiczek.chat.datastore.entities.User;
import com.spiczek.chat.shared.ClientService;
import com.spiczek.chat.shared.dto.UserDTO;
import com.spiczek.chat.shared.errors.ServiceError;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Piotr Siczek
 */
@Service("clientService")
public class ClientServiceImpl  implements ClientService {
    private Logger log = Logger.getLogger("ClientServiceImpl");
    private UserDAO userDAO;

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
        List<UserDTO> result = DtoBuilder.createUserDTOList(friends);
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

        return DtoBuilder.createUserDTO(friend);
    }

    @Override
    public void removeFriend(Long friendEntityKey, Long friendKey) {
        userDAO.removeFriend(friendEntityKey, friendKey);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}