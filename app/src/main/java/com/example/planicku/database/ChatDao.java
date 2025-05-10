package com.example.planicku.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.planicku.model.ChatItem;

import java.util.List;

@Dao
public interface ChatDao {
    @Insert
    void insert(ChatItem chatItem);

    @Delete
    void delete(ChatItem chatItem);

    @Query("SELECT * FROM chat_table ORDER BY id ASC")
    List<ChatItem> getAllChats();
}