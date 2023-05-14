package com.example.inigaram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Post;
import model.User;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<Post> posts;
    private Context context;
    private User user;

    public HomeAdapter(ArrayList<Post> posts, Context context, User user) {
        this.posts = posts;
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.fullNameTextView.setText(user.getFullName());
        holder.userNameTextView.setText(user.getUserName());
        holder.captionTextView.setText(post.getCaption());
        holder.profileImage.setImageResource(user.getProfilePicture());
        holder.postImage.setImageURI(post.getImageUri());

        holder.profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailProfileActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, userNameTextView, captionTextView;
        ImageView profileImage, postImage;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            fullNameTextView = itemView.findViewById(R.id.fullname);
            userNameTextView = itemView.findViewById(R.id.username);
            captionTextView = itemView.findViewById(R.id.post_caption);
            profileImage = itemView.findViewById(R.id.profile_image);
            postImage = itemView.findViewById(R.id.post_image);
        }
    }
}
