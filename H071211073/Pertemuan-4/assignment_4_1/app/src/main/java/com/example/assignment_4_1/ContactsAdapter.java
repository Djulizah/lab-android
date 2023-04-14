package com.example.assignment_4_1;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    ArrayList<Contacts> contacts;
    ClickListener clickListener;

    public ContactsAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        Contacts contact = contacts.get(position);

        //handling zoomIn image in MainActivity
        holder.ivProfile.setOnClickListener(view -> {
            Intent zoomIn = new Intent(holder.itemView.getContext(), ImageZoomActivity.class);
            zoomIn.putExtra("contact", contact);
            holder.itemView.getContext().startActivity(zoomIn);
        });

        holder.setData(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvChat, tvTime;
        ImageView ivProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvChat = itemView.findViewById(R.id.tv_chat);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }

        public void setData(Contacts contact) {
            tvName.setText(contact.getName());
            tvChat.setText(contact.getChats().get(contact.getChats().size()-1).getChat());
            tvTime.setText(contact.getChats().get(contact.getChats().size()-1).getTime());
            ivProfile.setImageResource(contact.getImg());
            itemView.setOnClickListener(view -> {
                clickListener.onItemClicked(contact);
            });

        }
    }

    interface ClickListener {
        void onItemClicked(Contacts contact);
    }
}
