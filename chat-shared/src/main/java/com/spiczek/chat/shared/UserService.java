package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by piotr on 2014-08-14.
 */
@RemoteServiceRelativePath("service/userService")
public interface UserService extends RemoteService {

    public String test(String data);

}
