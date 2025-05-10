package com.example.planicku.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_table")
public class ChatItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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