package ke.natujenge.chat.livechatserver.repository;

import ke.natujenge.chat.livechatserver.service.dto.ChatMessageDTO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChatMessageCacheRepository {
    private final Map<String, List<ChatMessageDTO>> cacheSessionMessagesMap = new HashMap<>();

    public void save(ChatMessageDTO chatMessage) {
        List<ChatMessageDTO> sessionMessages = cacheSessionMessagesMap.get(chatMessage.getSessionId());


        if (sessionMessages == null){
            sessionMessages = new ArrayList<>();
        }

        sessionMessages.add(chatMessage);

        cacheSessionMessagesMap.put(chatMessage.getSessionId(), sessionMessages);
    }

    public List<ChatMessageDTO> getNewMessages(String sessionId, long lastInMessageTime) {
        return cacheSessionMessagesMap.getOrDefault(sessionId, new ArrayList<>());
    }
}
