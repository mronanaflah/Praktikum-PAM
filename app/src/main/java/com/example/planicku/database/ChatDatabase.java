package com.example.planicku.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.planicku.model.ChatItem;

@Database(entities = {ChatItem.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {
    private static volatile ChatDatabase INSTANCE;
    public abstract ChatDao chatDao();

    public static ChatDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ChatDatabase.class, "chat_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}