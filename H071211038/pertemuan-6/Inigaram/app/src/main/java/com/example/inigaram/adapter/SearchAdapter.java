package com.example.inigaram.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inigaram.DetailProfileActivity;
import com.example.inigaram.R;
import com.example.inigaram.model.User;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private ArrayList<User> users;

    public SearchAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        User user = users.get(position);

        holder.fullNameTextView.setText(user.getFullName());
        holder.userNameTextView.setText(user.getUserName());
        holder.profileImage.setImageResource(user.getProfilePicture());

        holder.profileImage.setOnClickListener(v -> {
            User profile = new User(user.getUserId(), user.getFullName(), user.getUserName(), user.getProfilePicture());
            Intent intent = new Intent(context, DetailProfileActivity.class);
            intent.putExtra(DetailProfileActivity.KEY_PROFILE, profile);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, userNameTextView;
        ImageView profileImage;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            fullNameTextView = itemView.findViewById(R.id.fullname);
            userNameTextView = itemView.findViewById(R.id.username);
            profileImage = itemView.findViewById(R.id.profile_image);
        }
    }
}
