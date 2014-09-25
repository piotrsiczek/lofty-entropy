package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
public interface ClientServiceAsync {
    void getUserDetails(AsyncCallback<UserDTO> async);
    void getFriends(Long friendId, AsyncCallback<List<UserDTO>> async);
    void addFriend(Long friendEntityKey, String login, AsyncCallback<UserDTO> async);
    void removeFriend(Long friendEntityKey, Long friendKey, AsyncCallback<Void> async);
}
