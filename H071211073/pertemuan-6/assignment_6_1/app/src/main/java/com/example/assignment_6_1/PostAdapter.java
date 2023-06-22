package com.example.assignment_6_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    Context context;
    ArrayList<Post> postArray;

    public PostAdapter(Context context, ArrayList<Post> postArray) {
        this.context = context;
        this.postArray = postArray;
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
        Post post = postArray.get(position);
        holder.tvName.setText(post.getName());
        holder.tvCaption.setText(post.getCaption());

        String imgUrl = DataSource.posts.get(position).getImgPerson();
        Picasso.get().load(imgUrl).into(holder.imgPerson);

        String imgUri = DataSource.posts.get(position).getImgPost();
        Picasso.get().load(imgUri).into(holder.imgPost);
    }

    @Override
    public int getItemCount() {
        return postArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPerson, imgPost;
        TextView tvName, tvCaption;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPerson = itemView.findViewById(R.id.img_person);
            imgPost = itemView.findViewById(R.id.img_post);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCaption = itemView.findViewById(R.id.tv_caption);
        }
    }
}

