package com.example.assignment_6_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    ArrayList<User> userArray;

    public void setFilteredList(ArrayList<User> filteredList) {
        userArray = filteredList;
        notifyDataSetChanged();
    }

    public UserAdapter(Context context, ArrayList<User> userArray) {
        this.context = context;
        this.userArray = userArray;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = userArray.get(position);

        holder.tvName.setText(user.getName());
        holder.tvUname.setText(user.getUsername());

        String imgUrl = user.getImgUri();
        Picasso.get().load(imgUrl).into(holder.imgPerson);
    }

    @Override
    public int getItemCount() {
        return userArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPerson;
        TextView tvName, tvUname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPerson = itemView.findViewById(R.id.img_person);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUname = itemView.findViewById(R.id.tv_uname);
        }
    }
}

