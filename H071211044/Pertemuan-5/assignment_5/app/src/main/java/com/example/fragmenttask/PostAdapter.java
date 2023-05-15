package com.example.fragmenttask;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttask.models.Post;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<Post> listPost = new ArrayList<>();

    public void addItem(Post post) {
        listPost.add(post);
        notifyItemInserted(listPost.size() - 1);
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //mndeklarasikan layout utk recycler
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = listPost.get(position);
        holder.tvCaption.setText(post.getCaption());
        holder.ivPostPhoto.setImageURI(post.getImageUrl());

        holder.ivUserPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView ivUserPhoto;
        TextView tvFullName,tvUserName, tvCaption;
        ImageView ivPostPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivUserPhoto = itemView.findViewById(R.id.iv_user_photo);
            tvFullName = itemView.findViewById(R.id.tv_fullname);
            tvUserName = itemView.findViewById(R.id.tv_username);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            ivPostPhoto = itemView.findViewById(R.id.iv_post_photo);
        }
    }
}
