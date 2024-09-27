package com.example.loopj;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loopj.UserResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<UserResponse> users;
    private Context context;

    public UserAdapter(List<UserResponse> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        UserResponse userResponse = users.get(position);
        String fullName = userResponse.getFirstName() + " " + userResponse.getLastName();
        holder.tvName.setText(fullName);
        holder.tvEmail.setText(userResponse.getEmail());
        Glide.with(context)
                .load(userResponse.getAvatarUrl())
                .into(holder.ivAvatar);

        holder.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                toProfile.putExtra("id", String.valueOf(userResponse.getId()));
                System.out.println(userResponse.getId());
                holder.ivAvatar.getContext().startActivity(toProfile);
            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvEmail;
        private ImageView ivAvatar;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
