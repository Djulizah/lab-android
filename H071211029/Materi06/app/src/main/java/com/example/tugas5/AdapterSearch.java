package com.example.tugas5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<User> items = new ArrayList<>();
    public AdapterSearch(ArrayList<User> users){
        this.users = users;
    }
    public void filterList(String text){
        ArrayList<User> item = new ArrayList<>();
        for (User user : users){
            if (user.getUsername().toLowerCase().startsWith(text.toLowerCase())){
                item.add(user);
            }
        }
        if (!(item.isEmpty())){
            setFilteredList(item);
        }
    }
    public void setFilteredList(ArrayList<User> filteredList){
        this.items.clear();
        this.items.addAll(filteredList);
        notifyDataSetChanged();
    }
    public void add(User user){
        users.add(user);
    }


    @NonNull
    @Override
    public AdapterSearch.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearch.ViewHolder holder, int position) {
        User user = items.get(position);
        holder.search_fullName.setText(user.getFullName());
        holder.search_username.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView search_userPhoto;
        TextView search_username, search_fullName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            search_userPhoto = itemView.findViewById(R.id.search_userPhoto);
            search_username = itemView.findViewById(R.id.search_username);
            search_fullName = itemView.findViewById(R.id.search_fullName);
        }
    }
}
