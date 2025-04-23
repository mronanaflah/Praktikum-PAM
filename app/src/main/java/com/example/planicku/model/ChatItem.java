package com.example.planicku.model;

public class ChatItem {
    private int profileImageResId;
    private String name;
    private String lastMessage;
    private String time;

    public ChatItem(int profileImageResId, String name, String lastMessage, String time) {
        this.profileImageResId = profileImageResId;
        this.name = name;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public int getProfileImageResId() {
        return profileImageResId;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }
}