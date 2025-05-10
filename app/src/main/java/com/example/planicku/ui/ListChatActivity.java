package com.example.planicku.ui;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planicku.R;
import com.example.planicku.adapter.ChatAdapter;
import com.example.planicku.database.ChatDao;
import com.example.planicku.database.ChatDatabase;
import com.example.planicku.model.ChatItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatItem> chatItems;
    private ChatDao chatDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);

        chatDao = ChatDatabase.getDatabase(this).chatDao();
        recyclerView = findViewById(R.id.rv_listChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chatItems = chatDao.getAllChats();
        chatAdapter = new ChatAdapter(chatItems);
        recyclerView.setAdapter(chatAdapter);

        chatAdapter.setOnItemClickListener(position -> {
            ChatItem item = chatItems.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Hapus Chat?")
                    .setMessage("Apakah Anda yakin ingin menghapus chat ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        chatDao.delete(item);
                        chatItems.remove(position);
                        chatAdapter.notifyItemRemoved(position);
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });

        FloatingActionButton fabAdd = findViewById(R.id.fab_add_chat);
        fabAdd.setOnClickListener(v -> {
            ChatItem newItem = new ChatItem(R.drawable.profile1, "Kontak Baru", "Pesan Baru", "12:00");
            chatDao.insert(newItem);
            chatItems = chatDao.getAllChats(); // Refresh
            chatAdapter.setChatItems(chatItems);
            chatAdapter.notifyDataSetChanged();
        });
    }
}