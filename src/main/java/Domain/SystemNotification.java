package Domain;

import java.sql.Timestamp;

public class SystemNotification {

    private int id;
    private String topic;
    private User user;
    private boolean read;
    private String message;
    private String employeeMessage;
    private Timestamp createdAt;

    public SystemNotification() {}
    public SystemNotification(int id, String topic, User user, boolean read, String message, String employeeMessage, Timestamp createdAt) {
        this.id = id;
        this.topic = topic;
        this.user = user;
        this.read = read;
        this.message = message;
        this.employeeMessage = employeeMessage;
        this.createdAt = createdAt;
    }
    public int getId() { return id; }
    public String getTopic() { return topic; }
    public User getUser() { return user; }
    public boolean isRead() { return read; }
    public String getMessage() { return message; }
    public String getEmployeeMessage() { return employeeMessage; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setId(int id) { this.id = id; }
    public void setTopic(String topic) { this.topic = topic; }
    public void setUser(User user) { this.user = user; }
    public void setRead(boolean read) { this.read = read; }
    public void setMessage(String message) { this.message = message; }
    public void setEmployeeMessage(String employeeMessage) { this.employeeMessage = employeeMessage; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

}
