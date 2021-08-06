package ke.natujenge.chat.livechatserver.service;

import ke.natujenge.chat.livechatserver.repository.ChatSessionCacheRepository;
import ke.natujenge.chat.livechatserver.service.bot.ChatBotService;
import ke.natujenge.chat.livechatserver.service.dto.ChatSessionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatSessionService {
    private final Logger logger = LoggerFactory.getLogger(ChatSessionService.class);

    private final ChatSessionCacheRepository chatSessionCacheRepository;

    private final ChatBotService chatBotService;

    public ChatSessionService(ChatSessionCacheRepository chatSessionCacheRepository, ChatBotService chatBotService) {
        this.chatSessionCacheRepository = chatSessionCacheRepository;
        this.chatBotService = chatBotService;
    }

    public String getSessionId(String sessionId) {
        logger.info("Request to get session given sessionId :{}", sessionId);
        if (sessionId != null) {
            Optional<ChatSessionDTO> optional = chatSessionCacheRepository.findById(sessionId);

            if (optional.isPresent()) {
                logger.info("Found existing session : {}", optional.get());
                return sessionId;
            }
        }

        logger.info("Request to register a new session");
        String chatSessionId = UUID.randomUUID().toString();
        ChatSessionDTO chatSessionDTO = new ChatSessionDTO();
        chatSessionDTO.setId(chatSessionId);

        save(chatSessionDTO);

        chatBotService.initiateConversation(chatSessionId);

        return chatSessionId;
    }

    public ChatSessionDTO save(ChatSessionDTO chatSessionDTO) {
        logger.info("Request to save chat session : {}", chatSessionDTO);

        return chatSessionCacheRepository.save(chatSessionDTO);
    }

    public List<ChatSessionDTO> findAll() {
        logger.info("Request to get all chat sessions");
        return chatSessionCacheRepository.getSessions();
    }
}
