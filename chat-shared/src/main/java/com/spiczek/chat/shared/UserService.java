package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spiczek.chat.shared.dto.UserDTO;

/**
 * Created by piotr on 2014-08-14.
 */
@RemoteServiceRelativePath("service/userService")
public interface UserService extends RemoteService {
    public String test(String data);
    public UserDTO getUserDetails();
}
