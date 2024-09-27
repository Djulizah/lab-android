package com.example.tugas6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class AdapterChat extends
        RecyclerView.Adapter<AdapterChat.CardViewHolder> {
    private ArrayList<ModelChat> dataChat;

    ModelChat modelChat;

    public AdapterChat(ArrayList<ModelChat> dataChat)
    {
        this.dataChat = dataChat;
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ModelChat chat = dataChat.get(position);
        holder.tvNama.setText(chat.getNama());
        holder.tvChat.setText(chat.getChat().get(chat.getChat().size()-1).getMessage());
        holder.tvTime.setText(chat.getChat().get(chat.getChat().size()-1).getTime());
        Glide.with(holder.itemView.getContext())
                .load(chat.getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivFoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kirim = new Intent(holder.itemView.getContext(), ChatItem.class);
                System.out.println(chat.getChat().size());
                kirim.putExtra("chats",chat);
                holder.itemView.getContext().startActivity(kirim);

            }
        });


        holder.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foto = chat.getFoto();
                String nama = chat.getNama();
                System.out.println(foto);
                Intent toPicture = new Intent(holder.ivFoto.getContext(), PictureActivity.class);
                toPicture.putExtra("foto", foto );
                toPicture.putExtra("nama", nama);
                holder.ivFoto.getContext().startActivity(toPicture);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataChat.size();
    }

    public class CardViewHolder extends
            RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNama, tvChat, tvTime;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvChat = itemView.findViewById(R.id.tv_chat);
            tvTime = itemView.findViewById(R.id.tv_time);
        }


    }
}
