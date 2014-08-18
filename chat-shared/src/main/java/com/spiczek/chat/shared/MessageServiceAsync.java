package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Piotr Siczek
 */
public interface MessageServiceAsync {
    void getToken(Long id, AsyncCallback<String> async);
    void sendMessage(Long fromId, String fromName, Long toId, String message, AsyncCallback<Void> async);
}
