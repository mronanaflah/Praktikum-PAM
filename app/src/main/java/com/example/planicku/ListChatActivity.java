package com.example.planicku;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planicku.adapter.ChatAdapter;
import com.example.planicku.model.ChatItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListChatActivity extends AppCompatActivity {
    private RecyclerView rvChatList;
    private ChatAdapter chatAdapter;
    private List<ChatItem> chatItems;
    private List<ChatItem> defaultChatList = new ArrayList<>();
    private int nextIndex = 0;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);

        // Inisialisasi defaultChatList
        defaultChatList.add(new ChatItem(R.drawable.profile1, "Budi", "Apa kabar?", "10 minutes ago"));
        defaultChatList.add(new ChatItem(R.drawable.profile2, "Siti", "Sampai ketemu besok!", "Yesterday"));
        defaultChatList.add(new ChatItem(R.drawable.profile3, "Doni", "Oke, noted ya!", "12:15 PM"));
        defaultChatList.add(new ChatItem(R.drawable.profile4, "Lina", "Jangan lupa tugasnya", "1 week ago"));
        defaultChatList.add(new ChatItem(R.drawable.profile5, "Anda", "Terima kasih.", "2 weeks ago"));

        // Inisialisasi chatItems dan tampilkan langsung semua
        chatItems = new ArrayList<>();
        chatItems.addAll(defaultChatList);
        chatAdapter = new ChatAdapter(chatItems);
        rvChatList = findViewById(R.id.rv_listChat);
        rvChatList.setLayoutManager(new LinearLayoutManager(this));
        rvChatList.setAdapter(chatAdapter);

        // Tombol tambah chat
        fabAdd = findViewById(R.id.fab_add_chat);
        fabAdd.setOnClickListener(v -> {
            // Reset atau modulo agar bisa kembali dari awal
            ChatItem baru = defaultChatList.get(nextIndex % defaultChatList.size());
            nextIndex++;
            chatItems.add(baru);
            chatAdapter.notifyItemInserted(chatItems.size() - 1);
            rvChatList.scrollToPosition(chatItems.size() - 1);
        });

        // Listener hapus chat (tetap pakai AlertDialog Anda)
        chatAdapter.setOnItemClickListener(position -> {
            new AlertDialog.Builder(this)
                    .setTitle("Hapus Chat")
                    .setMessage("Yakin ingin menghapus chat ini?")
                    .setPositiveButton("Ya", (d,i) -> {
                        chatItems.remove(position);
                        chatAdapter.notifyItemRemoved(position);
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });
    }
}