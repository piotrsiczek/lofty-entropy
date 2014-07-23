package com.spiczek.chat.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("forntendService")
public interface forntendService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use forntendService.App.getInstance() to access static instance of forntendServiceAsync
     */
    public static class App {
        private static forntendServiceAsync ourInstance = GWT.create(forntendService.class);

        public static synchronized forntendServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
