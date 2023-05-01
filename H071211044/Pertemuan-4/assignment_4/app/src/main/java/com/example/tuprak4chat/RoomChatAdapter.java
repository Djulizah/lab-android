package com.example.tuprak4chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomChatAdapter extends RecyclerView.Adapter<RoomChatAdapter.RoomChatHolder> {
    private final ArrayList<RoomChat> roomChats;

    public RoomChatAdapter(ArrayList<RoomChat> list){
        this.roomChats = list;
    }

    @NonNull
    @Override
    public RoomChatAdapter.RoomChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roomview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bubblechat,parent, false);
        RoomChatHolder roomChatHolder = new RoomChatHolder(roomview);
        return roomChatHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomChatHolder holder, int position) {
        RoomChat roomCht = roomChats.get(position);
        holder.setData(roomCht);
    }

    @Override
    public int getItemCount() {
        return roomChats.size();
    }

    public class RoomChatHolder extends RecyclerView.ViewHolder {
        private TextView textchatRight, timeRight, textchatLeft,timeLeft ;
        public RoomChatHolder(@NonNull View itemView) {
            super(itemView);
            textchatRight = itemView.findViewById(R.id.textchata);
            textchatLeft = itemView.findViewById(R.id.textchatb);
            timeRight = itemView.findViewById(R.id.timea);
            timeLeft = itemView.findViewById(R.id.timeb);
        }
        public void setData (RoomChat roomChat){
            textchatRight.setText(roomChat.getMessageLeft());
            textchatLeft.setText(roomChat.getMessageRight());
            timeRight.setText(roomChat.getTimeLeft());
            timeLeft.setText(roomChat.getTimeRight());
        }
    }
}
