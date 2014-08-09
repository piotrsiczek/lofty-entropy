package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

public interface ClientServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);

    void getFriends(AsyncCallback<List<UserDTO>> async);

    void generateFriends(AsyncCallback<Void> async);
}
