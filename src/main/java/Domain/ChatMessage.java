package Domain;

import java.sql.Timestamp;

public class ChatMessage {

    private int id;
    private ChatRoom room;
    private User sender;
    private Bid bid;
    private String message;
    private Timestamp sentAt;
    private boolean isRead;

    public ChatMessage() {}

    public ChatMessage(int id, ChatRoom room, User sender, Bid bid, String message, Timestamp sentAt, boolean isRead) {
        this.id = id;
        this.room = room;
        this.sender = sender;
        this.bid = bid;
        this.message = message;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }

    public int getId() { return id; }
    public ChatRoom getRoom() { return room; }
    public User getSender() { return sender; }
    public Bid getBid() { return bid; }
    public String getMessage() { return message; }
    public Timestamp getSentAt() { return sentAt; }
    public boolean isRead() { return isRead; }
    public void setId(int id) { this.id = id; }
    public void setRoom(ChatRoom room) { this.room = room; }
    public void setSender(User sender) { this.sender = sender; }
    public void setBid(Bid bid) { this.bid = bid; }
    public void setMessage(String message) { this.message = message; }
    public void setSentAt(Timestamp sentAt) { this.sentAt = sentAt; }
    public void setRead(boolean isRead) { this.isRead = isRead; }
}
