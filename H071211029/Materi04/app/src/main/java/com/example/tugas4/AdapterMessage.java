package com.example.tugas4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.MessageViewHolder> {
    private ArrayList<ModelMessage> dataMessages;
    public AdapterMessage(ArrayList<ModelMessage> dataMessages){
        this.dataMessages = dataMessages;
    }
    @NonNull
    @Override
    public AdapterMessage.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_chat, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMessage.MessageViewHolder holder, int position) {
        ModelMessage message = dataMessages.get(position);
        holder.tvMessage.setText(message.getMessage());
        holder.tvTime.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        return dataMessages.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView tvMessage, tvTime;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.message);
            tvTime = itemView.findViewById(R.id.time);
        }
    }
}
