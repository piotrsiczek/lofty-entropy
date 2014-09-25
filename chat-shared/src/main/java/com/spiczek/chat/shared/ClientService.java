package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spiczek.chat.shared.dto.UserDTO;
import com.spiczek.chat.shared.errors.ServiceError;

import java.util.List;

/**
 * @author Piotr Siczek
 */
@RemoteServiceRelativePath("service/clientService")
public interface ClientService extends RemoteService {
    public UserDTO getUserDetails();
    public List<UserDTO> getFriends(Long friendId) throws ServiceError;
    public UserDTO addFriend(Long friendEntityKey, String login) throws ServiceError;
    public void removeFriend(Long friendEntityKey, Long friendKey);
}
