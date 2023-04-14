package com.example.tugas4;

public class Message {
    private int senderId;
    private int receiverId;
    private String text;
    private String time;

    public Message(int senderId, int receiverId, String text, String time) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.time = time;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

}
