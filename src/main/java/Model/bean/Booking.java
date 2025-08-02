package Model.bean;

import java.sql.Date;

public class Booking {
    private int id;
    private int userId;
    private int roomId;
    private Date date;
    private int from;
    private int to;
    private String purpose;
    private String teacherName;
    private String roomName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getFrom() {
        return from;
    }
    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }

    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
