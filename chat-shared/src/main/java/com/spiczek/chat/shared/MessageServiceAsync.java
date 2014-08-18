package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Piotr Siczek
 */
public interface MessageServiceAsync {
    void sendMessage(int fromId, int toId, String message, AsyncCallback<Void> async);
    void getToken(int id, AsyncCallback<String> async);
}
