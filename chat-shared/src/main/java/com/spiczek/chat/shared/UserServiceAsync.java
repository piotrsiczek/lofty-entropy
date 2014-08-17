package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spiczek.chat.shared.dto.UserDTO;

public interface UserServiceAsync {
    void test(String data, AsyncCallback<String> async);
    void getUserDetails(AsyncCallback<UserDTO> async);
}
