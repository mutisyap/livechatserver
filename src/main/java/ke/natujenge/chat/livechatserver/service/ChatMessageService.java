package ke.natujenge.chat.livechatserver.service;

import ke.natujenge.chat.livechatserver.repository.ChatMessageCacheRepository;
import ke.natujenge.chat.livechatserver.service.dto.ChatMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    private final ChatMessageCacheRepository chatMessageCacheRepository;
    private final Logger logger = LoggerFactory.getLogger(ChatMessageService.class);

    public ChatMessageService(ChatMessageCacheRepository chatMessageCacheRepository) {
        this.chatMessageCacheRepository = chatMessageCacheRepository;
    }

    public ChatMessageDTO saveMessage(ChatMessageDTO chatMessageDTO) {
        logger.info("Request to save message : {}", chatMessageDTO);
        if (chatMessageDTO.getSentAt() == null) {
            chatMessageDTO.setSentAt(System.currentTimeMillis());
        }
        chatMessageDTO.setReceivedAt(System.currentTimeMillis());

        chatMessageCacheRepository.save(chatMessageDTO);

        return chatMessageDTO;
    }

    public List<ChatMessageDTO> filterBySession(String session, Long lastInMessageTime){
        logger.info("Request to get new messages in session : {}, after lastInMessageTime : {}", session, lastInMessageTime);

        if (lastInMessageTime == null){
            lastInMessageTime = 0L;
        }

        return chatMessageCacheRepository.getNewMessages(session, lastInMessageTime);
    }
}
