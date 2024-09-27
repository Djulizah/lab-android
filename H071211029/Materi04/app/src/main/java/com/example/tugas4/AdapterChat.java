package com.example.tugas4;

import android.content.Intent;
import android.media.Image;
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

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.CardViewHolder> {
    private ArrayList<ModelChat> dataChat;

    public AdapterChat(ArrayList<ModelChat> dataChat){
        this.dataChat = dataChat;
    }
    @NonNull
    @Override
    public AdapterChat.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChat.CardViewHolder holder, int position) {
        ModelChat chat = dataChat.get(position);
        holder.tvName.setText(chat.getNama());
        holder.tvChat.setText(chat.getChat());
        holder.tvTime.setText(chat.getTime());

        Glide.with(holder.itemView.getContext()).load(chat.getFoto()).apply(new RequestOptions().
                override(350, 550)).into(holder.ivFoto);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("putNama", chat.getNama());
            intent.putExtra("putChat", chat.getChat());
            intent.putExtra("putFoto", chat.getFoto());
            intent.putExtra("putTime", chat.getTime());
            intent.putExtra("putNomor", chat.getNomor());
            intent.putExtra("putStatus", chat.getStatus());
            intent.putExtra("putStatusTime", chat.statusTime);

            holder.itemView.getContext().startActivity(intent);
        });

        holder.ivFoto.setOnClickListener(v -> {
            Intent intent = new Intent(holder.ivFoto.getContext(), ProfileImage.class);
            intent.putExtra("nama", chat.getNama());
            intent.putExtra("image", chat.getFoto());
            holder.ivFoto.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataChat.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView ivFoto;
        TextView tvName, tvChat, tvTime;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.foto);
            tvName = itemView.findViewById(R.id.name);
            tvChat = itemView.findViewById(R.id.chat);
            tvTime = itemView.findViewById(R.id.time);
        }
    }
}
