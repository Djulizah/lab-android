package com.example.task5_fragment;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task5_fragment.models.Upload;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    List<Upload> listPost = new ArrayList<>();

    //method utk menambahkan item ke listpost
    public void addItem(Upload upload) {
        listPost.add(upload);
        notifyItemInserted(listPost.size() - 1);
    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //mendeklarasikan layout untuk rv
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
        Upload upload = listPost.get(position);
        holder.tv_caption.setText(upload.getCaption());
        holder.iv_image.setImageURI(upload.getImageUrl());

        holder.iv_profile.setOnClickListener(v->{
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile, iv_image;
        TextView tv_fullname, tv_username, tv_caption, tv_uname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_caption = itemView.findViewById(R.id.tv_caption);
            tv_uname = itemView.findViewById(R.id.tv_uname);
        }
    }
}
