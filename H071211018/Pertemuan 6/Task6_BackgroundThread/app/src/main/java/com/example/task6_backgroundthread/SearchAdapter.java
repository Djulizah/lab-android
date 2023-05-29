package com.example.task6_backgroundthread;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task6_backgroundthread.models.User;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<User> listUser = new ArrayList<>();

    public void setListUser(ArrayList<User> listUser) {
        this.listUser.clear();
        this.listUser.addAll(listUser);
    }

    public void clearListUser () {this.listUser.clear();}

    public SearchAdapter() {
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        User user = listUser.get(position);
        holder.iv_profile.setImageResource(user.getImageUrl());
        holder.tv_fullName.setText(user.getFullName());
        holder.tv_userName.setText(user.getUserName());

        holder.iv_profile.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
            intent.putExtra("user", user);
            holder.itemView.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_fullName, tv_userName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_userName = itemView.findViewById(R.id.tv_userName);

        }
    }
}
