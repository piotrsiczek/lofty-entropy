package com.spiczek.chat.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spiczek.chat.shared.dto.MessageDTO;
import com.spiczek.chat.shared.dto.TalkDTO;

import java.util.List;

/**
 * @author Piotr Siczek
 */
@RemoteServiceRelativePath("service/messageService")
public interface MessageService extends RemoteService {
    public String getToken(Long id);
    public void sendMessage(Long fromId, String fromName, Long toId, Long talkId, String message);
    public Long createTalk(Long userKey, Long usesrChatKey, Long receiverChatKey, Long receiverKey);
    public Long createMessage(String text, String time, Long talkKey, Long senderKey);
    public List<TalkDTO> getTalks(Long userChatKey, Long receiverKey);
    public List<MessageDTO> getMessages(Long talkKey);
}
