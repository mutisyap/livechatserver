package ke.natujenge.chat.livechatserver.service.bot;

import ke.natujenge.chat.livechatserver.domain.enumeration.ChatMessagePurpose;
import ke.natujenge.chat.livechatserver.service.ChatMessageService;
import ke.natujenge.chat.livechatserver.service.dto.ChatMessageDTO;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    private final ChatMessageService chatMessageService;

    public ChatBotService(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    public void initiateConversation(String sessionId){
        String firstMessage = "karibu sana. You got any questions? I'm happy to help. \n" +
                "Before we get started, what's your name?";
        sendAutomatedMessage(sessionId, firstMessage, ChatMessagePurpose.NAME);

    }

    public void sendAutomatedMessage(String sessionId, String message, ChatMessagePurpose chatMessagePurpose){
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();

        chatMessageDTO.setSessionId(sessionId);
        chatMessageDTO.setMessage(message);
        chatMessageDTO.setResponsePurpose(ChatMessagePurpose.NAME);
        chatMessageDTO.setSourceAddress("SYSTEM");

        chatMessageService.saveMessage(chatMessageDTO);
    }
}

