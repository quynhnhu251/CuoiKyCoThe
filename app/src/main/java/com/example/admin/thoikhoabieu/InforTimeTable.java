package com.example.admin.thoikhoabieu;

/**
 * Created by admin on 4/25/2018.
 */

public class InforTimeTable {
    private String subject;
    private String room;
    private String roomNumber;

    public InforTimeTable(String subject, String room, String roomNumber) {
        this.subject = subject;
        this.room = room;
        this.roomNumber = roomNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


}
