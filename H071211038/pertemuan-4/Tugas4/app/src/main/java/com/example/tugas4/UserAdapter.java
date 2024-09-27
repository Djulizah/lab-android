package com.example.tugas4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private ArrayList<User> mUsers;
    private Context mContext;
    private final String KEY_USER_ID = "KEY_USER_ID";
    private final String KEY_PROFILE_PICTURE = "KEY_PROFILE_PICTURE";

    public UserAdapter(ArrayList<User> users, Context context) {
        mUsers = users;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_layout, parent, false);
            return new HeaderViewHolder(headerView);
        } else {
            View userView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_list_item, parent, false);
            return new UserViewHolder(userView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {

            User user = mUsers.get(position - 1);
            Message lastMessage = getLastMessageForUser(user.getId());

            ((UserViewHolder) holder).nameTextView.setText(user.getName());
            ((UserViewHolder) holder).lastMessage.setText(lastMessage.getText());
            ((UserViewHolder) holder).timestamp.setText(lastMessage.getTime());
            ((UserViewHolder) holder).profilePicture.setImageResource(user.getProfilePicture());
            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra(KEY_USER_ID, user.getId());
                mContext.startActivity(intent);
            });

            ((UserViewHolder) holder).profilePicture.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, ProfilePictureDetailActivity.class);
                intent.putExtra(KEY_USER_ID, user.getId());
                mContext.startActivity(intent);
            });
        }
    }

    private Message getLastMessageForUser(int userId) {
        MessageDataSource messageDataSource = new MessageDataSource();
        ArrayList<Message> messages = messageDataSource.getMessageForUser(userId);
        return messages.get(messages.size() - 1);
    }

    @Override
    public int getItemCount() {
        return mUsers.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    // ViewHolder for the header view
    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView lastMessage;
        private TextView timestamp;
        private ImageView profilePicture;

        UserViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.profile_name);
            lastMessage = itemView.findViewById(R.id.last_message);
            timestamp = itemView.findViewById(R.id.timestamp);
            profilePicture = itemView.findViewById(R.id.profile_picture);
        }
    }
}

