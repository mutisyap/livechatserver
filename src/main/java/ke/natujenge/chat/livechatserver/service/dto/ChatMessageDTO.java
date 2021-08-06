package ke.natujenge.chat.livechatserver.service.dto;

import ke.natujenge.chat.livechatserver.domain.enumeration.ChatMessagePurpose;

public class ChatMessageDTO {

    private String message;

    private String sessionId;

    private Long sentAt;

    private Long receivedAt;

    private String sourceAddress;

    private String destinationAddress;

    private ChatMessagePurpose purpose;

    private ChatMessagePurpose responsePurpose;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSentAt() {
        return sentAt;
    }

    public void setSentAt(Long sentAt) {
        this.sentAt = sentAt;
    }

    public Long getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Long receivedAt) {
        this.receivedAt = receivedAt;
    }

    public ChatMessagePurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(ChatMessagePurpose purpose) {
        this.purpose = purpose;
    }

    public ChatMessagePurpose getResponsePurpose() {
        return responsePurpose;
    }

    public void setResponsePurpose(ChatMessagePurpose responsePurpose) {
        this.responsePurpose = responsePurpose;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    @Override
    public String toString() {
        return "ChatMessageDTO{" +
                "message='" + message + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", sentAt=" + sentAt +
                ", receivedAt=" + receivedAt +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", purpose=" + purpose +
                ", responsePurpose=" + responsePurpose +
                '}';
    }
}
