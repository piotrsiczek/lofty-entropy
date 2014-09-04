package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Piotr Siczek
 */
public interface MessageServiceAsync {
    void getToken(Long id, AsyncCallback<String> async);
    void sendMessage(Long fromId, String fromName, Long toId, Long talkId, String message, AsyncCallback<Void> async);
    void createTalk(Long myChatKey, Long receiverChatKey, Long receiverKey, AsyncCallback<Long> async);
    void createMessage(String text, Long talkKey, Long senderKey, AsyncCallback<Long> async);
}
