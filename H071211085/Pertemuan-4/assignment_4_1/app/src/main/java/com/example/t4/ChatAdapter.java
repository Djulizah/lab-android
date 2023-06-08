package com.example.t4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatModel> chatModels;

    public ChatAdapter(ArrayList<ChatModel> chatModels) {
        this.chatModels = chatModels;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        TextView chatTv, timeTv;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            chatTv = itemView.findViewById(R.id.chatTv);
            timeTv = itemView.findViewById(R.id.timeTv);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView chatTv, timeTv;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            chatTv = itemView.findViewById(R.id.chatTv);
            timeTv = itemView.findViewById(R.id.timeTv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatModels.get(position).getReceiver()) {
            case 0:
                return 0;
            default:
                return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.other_chat_balloon, parent, false));
            default:
                return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_chat_balloon, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (chatModels.get(position).getReceiver()) {
            case 0:
                ((ViewHolder1) holder).chatTv.setText(chatModels.get(position).getMessage());
                ((ViewHolder1) holder).timeTv.setText(chatModels.get(position).getTimestamp());
                break;
            default:
                ((ViewHolder2) holder).chatTv.setText(chatModels.get(position).getMessage());
                ((ViewHolder2) holder).timeTv.setText(chatModels.get(position).getTimestamp());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }

}