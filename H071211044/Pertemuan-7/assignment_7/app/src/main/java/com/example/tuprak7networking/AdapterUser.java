package com.example.tuprak7networking;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {

    private final ArrayList<UserModel> users = new ArrayList<>();

    public void addUser (List<UserModel> users) {
        this.users.addAll(users);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_card, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.ViewHolder holder, int position) {
        UserModel userResponse = users.get(position);
        holder.tvUsername.setText(userResponse.getFirst_name() + " " + userResponse.getLast_name());
        holder.tvEmail.setText(userResponse.getEmail());
        Glide.with(holder.sivProfile.getContext()).load(userResponse.getProfileImage()).into(holder.sivProfile);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userResponse.getFirst_name() + " " + userResponse.getLast_name();
                String email = userResponse.getEmail();
                String profile = userResponse.getProfileImage();
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class );
                intent.putExtra("nama", username);
                intent.putExtra("email", email);
                intent.putExtra("profile", profile);
                intent.putExtra("id", String.valueOf(userResponse.getId()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvEmail;
        ShapeableImageView sivProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvEmail = itemView.findViewById(R.id.tv_email);
            sivProfile = itemView.findViewById(R.id.siv_profile);
        }
    }
}
