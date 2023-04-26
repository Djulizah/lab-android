package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ImageView ivFoto, back;
    TextView tvName, tvChat, tvTime;
    RecyclerView rvMessage;
    LinearLayout detail;
    ArrayList<ModelMessage> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivFoto = findViewById(R.id.foto);
        tvName = findViewById(R.id.name);
        tvChat = findViewById(R.id.chat);
        tvTime = findViewById(R.id.time);
        back = findViewById(R.id.back);
        detail = findViewById(R.id.detail);
        rvMessage = findViewById(R.id.rv_message);

        data.add(new ModelMessage("Dosen", "Mana mi tugas mu e", "12.00"));
        data.add(new ModelMessage("Dosen", "Kumpul mi", "14.12"));
        data.add(new ModelMessage("Dosen", "Sekarang", "14.22"));
        data.add(new ModelMessage("Dosen", "Malas ma tunggu i", "09.02"));
        data.add(new ModelMessage("Dosen", "Cptko", "06.00"));

        data.add(new ModelMessage("Edgar", "Adakah", "11.11"));
        data.add(new ModelMessage("Edgar", "Tugas teopel", "10.27"));
        data.add(new ModelMessage("Edgar", "Ini malam toh", "15.21"));
        data.add(new ModelMessage("Edgar", "Dl nya", "08.10"));
        data.add(new ModelMessage("Edgar", "23.59", "19.01"));

        data.add(new ModelMessage("Jones", "Spill beng", "12.21"));
        data.add(new ModelMessage("Jones", "Tugas", "09.52"));
        data.add(new ModelMessage("Jones", "Tugas praktikum", "14.17"));
        data.add(new ModelMessage("Jones", "Bahh", "12.06"));
        data.add(new ModelMessage("Jones", "Oke", "10.02"));

        data.add(new ModelMessage("Ayang", "Dimanaki", "11.01"));
        data.add(new ModelMessage("Ayang", "Masa we", "09.12"));
        data.add(new ModelMessage("Ayang", "Serius kah", "12.27"));
        data.add(new ModelMessage("Ayang", "Begitu nya", "11.56"));
        data.add(new ModelMessage("Ayang", "Hmm", "01.22"));

        data.add(new ModelMessage("Siapa ini", "Halo", "01.21"));
        data.add(new ModelMessage("Siapa ini", "Haii", "12.16"));
        data.add(new ModelMessage("Siapa ini", "Selamat pagi", "19.12"));
        data.add(new ModelMessage("Siapa ini", "Selamat malam", "05.51"));
        data.add(new ModelMessage("Siapa ini", "Selamat sore", "05.52"));

        data.add(new ModelMessage("Slot", "Haloo", "02.31"));
        data.add(new ModelMessage("Slot", "Selamat pagi", "23.23"));
        data.add(new ModelMessage("Slot", "Ada yang baru nich", "23.52"));
        data.add(new ModelMessage("Slot", "Diskon ramadhan", "23.52"));
        data.add(new ModelMessage("Slot", "Diskon 80%", "21.14"));

        data.add(new ModelMessage("Hikmal", "Dimanako", "21.41"));
        data.add(new ModelMessage("Hikmal", "Pergi makan e", "13.35"));
        data.add(new ModelMessage("Hikmal", "Nd puasa jko toh", "13.51"));
        data.add(new ModelMessage("Hikmal", "Gass mi e", "21.12"));
        data.add(new ModelMessage("Hikmal", "Otw", "14.14"));

        data.add(new ModelMessage("Agus", "Siapa ini", "21.41"));
        data.add(new ModelMessage("Agus", "Dari mana dapat nomor ku", "13.35"));
        data.add(new ModelMessage("Agus", "Salah sambung", "13.51"));
        data.add(new ModelMessage("Agus", "Bukan saya", "21.12"));
        data.add(new ModelMessage("Agus", "Salah orangki", "14.14"));

        data.add(new ModelMessage("Sudirman", "Bismillah", "21.41"));
        data.add(new ModelMessage("Sudirman", "Haloo", "13.35"));
        data.add(new ModelMessage("Sudirman", "Saya sudirman", "13.51"));
        data.add(new ModelMessage("Sudirman", "Salam kenal", "21.12"));
        data.add(new ModelMessage("Sudirman", "Hmm", "14.14"));

        data.add(new ModelMessage("Kang Servis AC", "Haii", "22.46"));
        data.add(new ModelMessage("Kang Servis AC", "Manaki", "23.36"));
        data.add(new ModelMessage("Kang Servis AC", "Menunggu ma", "15.21"));
        data.add(new ModelMessage("Kang Servis AC", "Ku tunggu ki", "11.11"));
        data.add(new ModelMessage("Kang Servis AC", "Hmm", "15.12"));

        data.add(new ModelMessage("Selingkuhan", "Shhtt", "13.21"));
        data.add(new ModelMessage("Selingkuhan", "Ada pacarku", "11.25"));
        data.add(new ModelMessage("Selingkuhan", "Tunggu mi", "21.41"));
        data.add(new ModelMessage("Selingkuhan", "Bentar lagi", "11.22"));
        data.add(new ModelMessage("Selingkuhan", "Ayokmi", "14.54"));

        Intent intent = getIntent();

        String nama = intent.getStringExtra("putNama");
        String chat = intent.getStringExtra("putChat");
        int foto = intent.getIntExtra("putFoto", 0);
        String time = intent.getStringExtra("putTime");
        String nomor = intent.getStringExtra("putNomor");
        String status = intent.getStringExtra("putStatus");
        String statusTime = intent.getStringExtra("putStatusTime");

        ArrayList<ModelMessage> listMessage = filterMessagesBySender(data, nama);
        AdapterMessage messageAdapter = new AdapterMessage(listMessage);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        rvMessage.setAdapter(messageAdapter);

        tvName.setText(nama);
        tvChat.setText(chat);
        tvTime.setText(time);

        Glide.with(this).load(foto).into(ivFoto);

        back.setOnClickListener(v -> {
            finish();
        });

        detail.setOnClickListener(v -> {
            Intent kirimStatus = new Intent(this, StatusActivity.class);
            kirimStatus.putExtra("putNama", nama);
            kirimStatus.putExtra("putFoto", foto);
            kirimStatus.putExtra("putNomor", nomor);
            kirimStatus.putExtra("putStatus", status);
            kirimStatus.putExtra("putStatusTime", statusTime);
            startActivity(kirimStatus);
        });
    }
    public ArrayList<ModelMessage> filterMessagesBySender(ArrayList<ModelMessage> messages, String senderName) {
        ArrayList<ModelMessage> filteredMessages = new ArrayList<>();
        for (ModelMessage message : messages) {
            if (message.getName().equals(senderName)) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }
}