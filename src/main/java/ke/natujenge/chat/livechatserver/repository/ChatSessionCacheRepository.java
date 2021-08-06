package ke.natujenge.chat.livechatserver.repository;

import ke.natujenge.chat.livechatserver.service.dto.ChatSessionDTO;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChatSessionCacheRepository {
    private final Map<String, ChatSessionDTO> chatSessionMap = new HashMap<>();

    public List<ChatSessionDTO> getSessions(){
        return new ArrayList<>(chatSessionMap.values());
    }

    public ChatSessionDTO save(ChatSessionDTO chatSessionDTO){
        chatSessionMap.put(chatSessionDTO.getId(), chatSessionDTO);

        return chatSessionDTO;
    }

    public Optional<ChatSessionDTO> findById(String sessionId){
        return Optional.ofNullable(chatSessionMap.get(sessionId));
    }
}
