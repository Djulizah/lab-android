package com.example.assignment_7_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<UserResponse> data;
    ClickListener clickListener;

    public UserAdapter(List<UserResponse> data) {
        this.data = data;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserResponse userResponse = data.get(position);
        holder.tvname.setText(userResponse.getFirstName() + userResponse.getLastName());
        holder.tvemail.setText(userResponse.getEmail());
        Glide.with(holder.itemView.getContext())
                .load(userResponse.getAvatarUrl())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClicked(userResponse);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvname, tvemail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_avatar);
            tvname = itemView.findViewById(R.id.tv_name);
            tvemail = itemView.findViewById(R.id.tv_email);
        }

    }

    interface ClickListener {
        void onItemClicked(UserResponse userResponse);
    }
}

