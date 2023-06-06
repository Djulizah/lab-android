package com.example.prak7_retrofit;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.BreakIterator;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CardViewHolder>{
    private ArrayList<UserResponse> dataUser = new ArrayList<>();
//    public UserAdapter(ArrayList<UserResponse> dataUser) {
//        this.dataUser = dataUser;
//    }

    public void addUser (ArrayList<UserResponse> users) {
        dataUser.addAll(users);

    }



    @NonNull
    @Override
    public UserAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.CardViewHolder holder, int position) {
        UserResponse userResponse = dataUser.get(position);
        System.out.println(userResponse.getEmail());
        holder.tv_fullName.setText(userResponse.getFirstName());
        holder.tv_email.setText(userResponse.getEmail());
        Glide.with(holder.iv_profil.getContext()).load(userResponse.getAvatarUrl()).apply(new RequestOptions()
                .override(350, 550)).into(holder.iv_profil);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ActivityProfile.class);
            intent.putExtra("id", String.valueOf(userResponse.getId()));
            holder.itemView.getContext().startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return dataUser.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fullName, tv_email;
        ImageView iv_profil;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profil = itemView.findViewById(R.id.iv_profil);
            tv_fullName = itemView.findViewById(R.id.tv_fullName);
            tv_email = itemView.findViewById(R.id.tv_email);
        }

    }
}
