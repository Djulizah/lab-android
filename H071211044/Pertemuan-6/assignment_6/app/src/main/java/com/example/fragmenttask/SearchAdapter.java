package com.example.fragmenttask;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmenttask.models.User;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private ArrayList<User> listuser = new ArrayList<>();

    public void setListuser(ArrayList<User> listuser) {
        this.listuser.clear();
        this.listuser.addAll(listuser);
    }

    public void clearListUser (){
        this.listuser.clear();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        User user = listuser.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvFullname.setText(user.getFullname());
        holder.ivProfil.setImageResource(user.getImageUrl());

        holder.ivProfil.setOnClickListener(v -> {
            Intent intent = new Intent((holder.itemView.getContext()), ProfileActivity.class);
            intent.putExtra("user", user);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listuser.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView ivProfil;
        TextView tvFullname, tvUsername;

        public ViewHolder(View itemView) {
            super(itemView);
            ivProfil = itemView.findViewById(R.id.iv_profil);
            tvFullname = itemView.findViewById(R.id.tv_fullname);
            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}

