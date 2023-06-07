package com.example.tugas5;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<Add> adds = new ArrayList<>();
    public void add(Add add){
        adds.add(add);
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Add add = adds.get(position);
        holder.tv_caption.setText(add.getCaption());
        holder.iv_postPhoto.setImageURI(add.getImage());

        holder.iv_userPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return adds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_caption, tv_fullName, tv_username;
        ImageView iv_postPhoto, iv_userPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_caption = itemView.findViewById(R.id.tv_caption);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_username = itemView.findViewById(R.id.tvUsername);
            iv_postPhoto = itemView.findViewById(R.id.iv_postPhoto);
            iv_userPhoto = itemView.findViewById(R.id.iv_userPhoto);
        }
    }
}
