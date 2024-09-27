package com.example.tp5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp5.models.User;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<User> listUser = new ArrayList<>();
    public void setListUser(List<User> listUser){
        this.listUser.clear();
        this.listUser.addAll(listUser);
    }


    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        User user = listUser.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvFullname.setText(user.getFullname());
        holder.svFoto.setImageResource(user.getImageUrl());

        holder.svFoto.setOnClickListener(view -> {
            Intent intent = new Intent((holder.itemView.getContext()), profile.class);
            intent.putExtra("user", user);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView svFoto;
        TextView tvFullname;
        TextView tvUsername;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            svFoto = itemView.findViewById(R.id.svFoto);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvUsername = itemView.findViewById(R.id.tvUsername);
        }
    }
}
