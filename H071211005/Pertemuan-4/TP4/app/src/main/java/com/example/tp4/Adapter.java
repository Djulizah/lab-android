package com.example.tp4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ChatVH> {

    private ArrayList<Chat> userlist;
    public Adapter (ArrayList<Chat> userlist) { this.userlist=userlist;}

    @NonNull
    @Override
    public Adapter.ChatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent,false);
        ChatVH chatVH = new ChatVH(chatView);
        return chatVH;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ChatVH holder, int position) {
        Chat chat = userlist.get(position);
        holder.setData(chat);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MainActivity2.class);
            intent.putExtra("chat", chat);
            view.getContext().startActivity(intent);
        });

        holder.im1.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MainActivity4.class);
            intent.putExtra("chat", chat);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
    public class ChatVH extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvMsg;
        private final TextView tvTime;
        private final ImageView im1;

        public ChatVH(View chatView) {
            super(chatView);
            tvName = chatView.findViewById(R.id.tvName);
            tvMsg = chatView.findViewById(R.id.tvMsg);
            tvTime = chatView.findViewById(R.id.tvTime);
            im1 = chatView.findViewById(R.id.im1);
        }
        public void setData(Chat chat) {
            tvName.setText(chat.getTvName());
            tvTime.setText(chat.getTvTime());
            im1.setImageResource(chat.getIm1());
            //mengambil chat terakhir dan memasukkannya ke text chat
            tvMsg.setText(chat.getListMsg().get(chat.getListMsg().size()-1).getTvMess());
        }
    }
}
