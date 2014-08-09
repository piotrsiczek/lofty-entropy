package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spiczek.chat.shared.dto.UserDTO;

import java.util.List;

@RemoteServiceRelativePath("clientService")
public interface ClientService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);
    List<UserDTO> getFriends();
    void generateFriends();


//    /**
//     * Utility/Convenience class.
//     * Use clientService.App.getInstance() to access static instance of clientServiceAsync
//     */
//    public static class App {
//      private static ClientServiceAsync ourInstance = GWT.create(ClientService.class);
//
//      public static synchronized ClientServiceAsync getInstance() {
//        return ourInstance;
//      }
//    }
}
