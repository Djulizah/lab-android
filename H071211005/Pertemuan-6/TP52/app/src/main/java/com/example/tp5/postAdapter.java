package com.example.tp5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp5.models.Post;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> {
    List<Post> listPost = new ArrayList<>();
    public postAdapter (List<Post> listPost){
        this.listPost = listPost;

    }

    @NonNull
    @Override
    public postAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postAdapter.ViewHolder holder, int position) {
        Post post = listPost.get(position);
        holder.tvCapt.setText(post.getCaption());
        holder.ivPost.setImageURI(post.getImageUrl());
        holder.tvName.setText(post.getUser().getFullname());
        holder.tvUser.setText(post.getUser().getUsername());
        holder.ivProfil.setImageResource(post.getUser().getImageUrl());

        holder.ivProfil.setOnClickListener(view -> {
            Intent intent = new Intent((holder.itemView.getContext()), profile.class);
            intent.putExtra("user", post.getUser());
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivProfil;
        TextView tvName, tvUser, tvCapt;
        ImageView ivPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfil = itemView.findViewById(R.id.ivProfil);
            tvName = itemView.findViewById(R.id.tvName);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvCapt = itemView.findViewById(R.id.tvCapt);
            ivPost = itemView.findViewById(R.id.ivPost);

        }
    }
}
