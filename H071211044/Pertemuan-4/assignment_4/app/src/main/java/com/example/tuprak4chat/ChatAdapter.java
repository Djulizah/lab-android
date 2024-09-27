package com.example.tuprak4chat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private final ArrayList<Chat> chats;

    public ChatAdapter(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.setData(chat);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);

                intent.putExtra("chat", chat);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.ivprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),MainActivity4.class);
                intent.putExtra("chat", chat);
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivprofil;
        TextView tvnama, tvchat, tvtime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivprofil = itemView.findViewById(R.id.iv_profil);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvchat = itemView.findViewById(R.id.tv_chat);
            tvtime = itemView.findViewById(R.id.tv_time);
        }

        public void setData(Chat chat) {
            ivprofil.setImageResource(chat.getPhoto());
            tvnama.setText(chat.getName());
            tvtime.setText(chat.getTime());
            //mengambil cht terakhir dan memasukkannya ke text chat
            int lastIndex = chat.getListChat().size()-1;
            tvchat.setText(chat.getListChat().get(0).getMessageLeft());

        }
    }
}
