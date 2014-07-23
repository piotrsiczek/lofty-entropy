package com.spiczek.chat.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by piotr on 2014-04-27.
 */
@RemoteServiceRelativePath("MessageService")
public interface MessageService extends RemoteService {
    /**
     * Utility/Convenience class.
     * Use MessageService.App.getInstance() to access static instance of MessageServiceAsync
     */
    public void sendMessage(int fromId, int toId, String message);

    public static class App {
        private static final MessageServiceAsync ourInstance = (MessageServiceAsync) GWT.create(MessageService.class);

        public static MessageServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
