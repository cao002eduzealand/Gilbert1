package Domain;

import java.sql.Timestamp;

public class ChatRoom {
    private int id;
    private User user1;
    private User user2;
    private Timestamp createdAt;


    public ChatRoom() {}
    public ChatRoom(int id, User user1, User user2, Timestamp createdAt) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser1() {
        return user1;
    }
    public void setUser1(User user1) {
        this.user1 = user1;
    }
    public User getUser2() {
        return user2;
    }
    public void setUser2(User user2) {
        this.user2 = user2;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
