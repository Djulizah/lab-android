package com.example.tp7;

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

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {

    private final ArrayList<UserResponse> users = new ArrayList<>();
    public void addUser (List<UserResponse> users) {
        this.users.addAll(users);
    }


    @NonNull
    @Override
    public AdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.carditem, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRV.ViewHolder holder, int position) {
        UserResponse userResponse = users.get(position);
        holder.tvName.setText(userResponse.getFirstName()+ " "+userResponse.getLastName());
        holder.tvEmail.setText(userResponse.getEmail());
        Glide.with(holder.itemView.getContext())
                .load(userResponse.getAvatarUrl())
                .into(holder.svProfile);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
            intent.putExtra("id", userResponse.getId());
            holder.itemView.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvEmail;
        ShapeableImageView svProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            svProfile = itemView.findViewById(R.id.svProfile);
        }
    }
}
