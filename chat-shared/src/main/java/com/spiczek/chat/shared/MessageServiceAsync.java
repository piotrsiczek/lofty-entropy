package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
public interface MessageServiceAsync {
    void getToken(Long id, AsyncCallback<String> async);
    void sendMessage(Long fromId, String fromName, Long toId, Long talkId, String message, AsyncCallback<Void> async);
    void createTalk(Long userKey, Long usesrChatKey, Long receiverChatKey, Long receiverKey, AsyncCallback<Long> async);
    void createMessage(String text, Long talkKey, Long senderKey, AsyncCallback<Long> async);
    void getTalks(Long userChatKey, Long receiverKey, AsyncCallback<List<TalkDTO>> async);
    void getMessages(Long talkKey, AsyncCallback<List<MessageDTO>> async);
}
