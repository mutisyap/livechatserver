package ke.natujenge.chat.livechatserver.web.rest;

import ke.natujenge.chat.livechatserver.service.ChatMessageService;
import ke.natujenge.chat.livechatserver.service.ChatSessionService;
import ke.natujenge.chat.livechatserver.service.dto.ChatMessageDTO;
import ke.natujenge.chat.livechatserver.service.dto.ChatSessionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatResource {
    private final Logger logger = LoggerFactory.getLogger(ChatResource.class);

    private final ChatSessionService chatSessionService;

    private final ChatMessageService chatMessageService;

    public ChatResource(ChatSessionService chatSessionService, ChatMessageService chatMessageService) {
        this.chatSessionService = chatSessionService;
        this.chatMessageService = chatMessageService;
    }

    @PostMapping("/chat")
    public ChatMessageDTO sendMessage(@RequestBody ChatMessageDTO chatMessageDTO, @CookieValue(required = false) String chatSessionId, HttpServletResponse httpServletResponse) {
        logger.info("REST request to send message : {} using session : {}", chatMessageDTO, chatSessionId);

        String sessionId = chatSessionService.getSessionId(chatSessionId);

        if (!sessionId.equalsIgnoreCase(chatSessionId)) {
            chatSessionId = sessionId;

            Cookie cookie = new Cookie("chatSessionId", chatSessionId);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            cookie.setSecure(false);


            httpServletResponse.addCookie(cookie);
        }

        chatMessageDTO.setSessionId(chatSessionId);
        return chatMessageService.saveMessage(chatMessageDTO);
    }

    @GetMapping("/sessions")
    public List<ChatSessionDTO> getChatSessions() {
        logger.info("REST request to get all the chat sessions");

        return chatSessionService.findAll();
    }

    @GetMapping("/messages")
    public List<ChatMessageDTO> getMessages(@RequestParam(required = false) String sessionId, @RequestParam(required = false) Long lastInMessageTime, @CookieValue (required = false, value = "chatSessionId") String cookieSessionId) {
        logger.info("REST request to get messages in session : {}, after lastInMessageTime : {}", sessionId, lastInMessageTime);

        if (sessionId == null){
            // use cookie id
            sessionId = cookieSessionId;
        }

        return chatMessageService.filterBySession(sessionId, lastInMessageTime);

    }
}
