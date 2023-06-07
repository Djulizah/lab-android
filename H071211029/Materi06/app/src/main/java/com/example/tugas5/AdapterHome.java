package com.example.tugas5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {
    ArrayList<Add> adds = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    public AdapterHome(ArrayList<User> users){
        this.users = users;
    }
    public void add(User user){
        users.add(user);
    }
    @NonNull
    @Override
    public AdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHome.ViewHolder holder, int position) {
//        Add add = adds.get(position);
        User user = users.get(position);
        holder.tv_caption.setText(user.getAdd().getCaption());
        holder.iv_userPhoto.setImageResource(user.getProfilImage());
        if (user.getAdd().getImage() != null){
            holder.iv_postPhoto.setImageURI(user.getAdd().getImage());
        }else{
            holder.iv_postPhoto.setImageResource(user.getAdd().getImageDrawable());
        }
        holder.tv_fullName.setText(user.getFullName());
        holder.tv_username.setText(user.getUsername());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_caption, tv_fullName, tv_username;
        ImageView iv_postPhoto, iv_userPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_caption = itemView.findViewById(R.id.tv_caption);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_username = itemView.findViewById(R.id.tv_username);
            iv_postPhoto = itemView.findViewById(R.id.iv_postPhoto);
            iv_userPhoto = itemView.findViewById(R.id.iv_userPhoto);
        }
    }
}
