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
    public String test(String data);
    public UserDTO getUserDetails();
    String getMessage(String msg);
    //List<UserDTO> getFriends();
    //List<UserDTO> getFriends(Long startId, int size);
    List<UserDTO> getFriends(Long friendId) throws ServiceError;
    void generateFriends();
    UserDTO addFriend(Long friendEntityKey, String login) throws ServiceError;
    void removeFriend(Long friendEntityKey, Long friendKey);
}
