package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
public interface ClientServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);

    //void getFriends(AsyncCallback<List<UserDTO>> async);

    //void getFriends(Long startId, int size, AsyncCallback<List<UserDTO>> async);

    void generateFriends(AsyncCallback<Void> async);

    void test(String data, AsyncCallback<String> async);

    void getUserDetails(AsyncCallback<UserDTO> async);

    void getFriends(Long friendId, AsyncCallback<List<UserDTO>> async);
}
