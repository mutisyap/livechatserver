package ke.natujenge.chat.livechatserver.service.dto;

public class ChatSessionDTO {

    private String id;

    private String userFullName;

    private String userEmail;

    private String userPhoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    @Override
    public String toString() {
        return "ChatSessionDTO{" +
                "cookieId='" + id + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }
}
