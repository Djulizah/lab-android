package com.example.tugas6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBubble extends
        RecyclerView.Adapter<AdapterBubble.CardViewHolder> {

    private ArrayList<ModelBubble> dataBubble;

    public AdapterBubble(ArrayList<ModelBubble> dataBubble)
    {
        this.dataBubble = dataBubble;
    }

    @NonNull
    @Override
    public AdapterBubble.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        switch ()
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_chat, parent, false);
        return new AdapterBubble.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBubble.CardViewHolder holder, int position) {
        ModelBubble modelBubble = dataBubble.get(position);
        holder.tvChat.setText(modelBubble.getMessage());
        holder.tvTime.setText(modelBubble.getTime());
    }

    @Override
    public int getItemCount() {
        return dataBubble.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView tvChat, tvTime;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChat = itemView.findViewById(R.id.tv_chat);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
