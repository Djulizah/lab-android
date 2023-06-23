package com.example.assignment_5_1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    ArrayList<Post> postsArray;

    public PostAdapter(Context context, ArrayList<Post> postsArray) {
        this.context = context;
        this.postsArray = postsArray;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = postsArray.get(position);
        holder.tvCaption.setText(post.caption);
        holder.imgPost.setImageURI(Uri.parse(post.getImgUri()));
    }

    @Override
    public int getItemCount() {
        return postsArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPost;
        TextView tvCaption;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.img_post);
            tvCaption = itemView.findViewById(R.id.tv_caption);
        }
    }
}

