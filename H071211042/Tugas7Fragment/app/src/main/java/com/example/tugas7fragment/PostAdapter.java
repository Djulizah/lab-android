package com.example.tugas7fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas7fragment.models.User;
import com.example.tugas7fragment.models.User;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private final ArrayList<User> users = new ArrayList<>();


    public void addItem(User user) {
        users.add(user);
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvFullName.setText(user.getFullName());
        holder.ivUserPhoto.setImageResource(user.getPhotoRes());
        holder.tvCaption.setText(user.getPost().getCaption());
        holder.ivPostPhoto.setImageURI(user.getPost().getImageUrl());

        holder.ivUserPhoto.setOnClickListener(v->{
            Intent toProfile = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(toProfile);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivUserPhoto;
        TextView tvFullName, tvUsername, tvCaption;
        ImageView ivPostPhoto;


        ViewHolder(View view){
            super(view);
            ivPostPhoto = view.findViewById(R.id.iv_post_photo);
            ivUserPhoto = view.findViewById(R.id.iv_user_photo);
            tvUsername = view.findViewById(R.id.tv_username);
            tvFullName = view.findViewById(R.id.tv_fullname);
            tvCaption = view.findViewById(R.id.tv_caption);
        }
    }
}
