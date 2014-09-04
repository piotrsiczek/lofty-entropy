package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Piotr Siczek
 */
@RemoteServiceRelativePath("service/messageService")
public interface MessageService extends RemoteService {
    public String getToken(Long id);
    public void sendMessage(Long fromId, String fromName, Long toId, Long talkId, String message);
    public Long createTalk(Long myChatKey, Long receiverChatKey, Long receiverKey);
    public Long createMessage(String text, Long talkKey, Long senderKey);
}
