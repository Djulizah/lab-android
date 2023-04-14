package com.example.tugas4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Message> messages;

    private Context context;

    public static final int VIEW_TYPE_SENDER = 0;
    public static final int VIEW_TYPE_RECEIVER = 1;

    public MessageAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        return (message.getSenderId() == 0) ? VIEW_TYPE_SENDER : VIEW_TYPE_RECEIVER;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = viewType == VIEW_TYPE_SENDER ? R.layout.item_chat_sender : R.layout.item_chat_receiver;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return viewType == VIEW_TYPE_SENDER ? new SenderViewHolder(itemView) : new ReceiverViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder instanceof SenderViewHolder) {
            SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
            senderViewHolder.messageTextView.setText(message.getText());
            senderViewHolder.timestampTextView.setText(message.getTime());
        }else { // untuk receiver
            ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
            receiverViewHolder.messageTextView.setText(message.getText());
            receiverViewHolder.timestampTextView.setText(message.getTime());

        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView timestampTextView;

        SenderViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.sender_message);
            timestampTextView = itemView.findViewById(R.id.sender_timestamp);
        }
    }

    public static class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView timestampTextView;

        ReceiverViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.receiver_message);
            timestampTextView = itemView.findViewById(R.id.receiver_timestamp);
        }
    }
}