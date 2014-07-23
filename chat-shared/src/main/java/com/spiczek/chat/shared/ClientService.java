package com.spiczek.chat.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("clientService")
public interface ClientService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use clientService.App.getInstance() to access static instance of clientServiceAsync
     */
    public static class App {
      private static ClientServiceAsync ourInstance = GWT.create(ClientService.class);

      public static synchronized ClientServiceAsync getInstance() {
        return ourInstance;
      }
    }
}
