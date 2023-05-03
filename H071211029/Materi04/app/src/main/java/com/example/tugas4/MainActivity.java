package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvChat;
    private ArrayList<ModelChat> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChat = findViewById(R.id.rv_chat);

        ModelChat chat1 = new ModelChat("Dosen", "Mana mi tugas mu e", R.drawable.foto1, "20.12", "082184188483", "Sibuk", "February 2, 2021");
        data.add(chat1);
        ModelChat chat2 = new ModelChat("Edgar", "Adakah tugas teopel", R.drawable.foto2, "12.31", "08748184829894", "Sok Sibuk", "December 20, 2009");
        data.add(chat2);
        ModelChat chat3 = new ModelChat("Jones", "HBD sob", R.drawable.foto3, "08.00", "078782849193", "Haii", "Maret 21, 2019");
        data.add(chat3);
        ModelChat chat4 = new ModelChat("Ayang", "Dimanaki", R.drawable.foto4, "22.53", "081204830593", "Busy", "May 31, 2020");
        data.add(chat4);
        ModelChat chat5 = new ModelChat("Siapa ini", "Haloo, selamat sore", R.drawable.foto5, "09.42", "084910480384", "Nothing", "April 21, 2012");
        data.add(chat5);
        ModelChat chat6 = new ModelChat("Slot", "Diskon ramadhan", R.drawable.foto6, "23.22", "084193949583", "Haloo", "January 21, 2019");
        data.add(chat6);
        ModelChat chat7 = new ModelChat("Hikmal", "Makan e", R.drawable.foto7, "13.10", "081293910349", "Huwaaa", "Juny 13, 2020");
        data.add(chat7);
        ModelChat chat8 = new ModelChat("Agus", "Ngikut ja saya", R.drawable.foto8, "17.00", "08398381939", "Hmm", "November 2, 2023");
        data.add(chat8);
        ModelChat chat9 = new ModelChat("Sudirman", "Jaya jaya jaya", R.drawable.foto9, "18.14", "089293930201", "Apakah e", "April 12, 2019");
        data.add(chat9);
        ModelChat chat10 = new ModelChat("Kang Servis AC", "500k pak?", R.drawable.foto10, "12.17", "089193910349", "Adakah", "July 29, 2023");
        data.add(chat10);
        ModelChat chat11 = new ModelChat("Selingkuhan", "Jadi kapan?", R.drawable.foto11, "09.22", "08929384931", "Apa dih", "February 09, 2012");
        data.add(chat11);

        rvChat.setLayoutManager(new LinearLayoutManager(this));
        AdapterChat chatAdapter = new AdapterChat(data);
        rvChat.setAdapter(chatAdapter);
    }
}