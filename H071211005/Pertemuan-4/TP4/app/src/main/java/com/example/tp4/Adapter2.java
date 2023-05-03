package com.example.tp4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.RoomVH> {
    private ArrayList<Room> userlist2;
    public Adapter2 (ArrayList<Room> userlist2) {this.userlist2=userlist2;}

    @NonNull
    @Override
    public Adapter2.RoomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roomview = LayoutInflater.from(parent.getContext()).inflate(R.layout.designroom,parent, false);
        RoomVH roomVH = new RoomVH(roomview);
        return roomVH;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.RoomVH holder, int position) {
        Room room = userlist2.get(position);
        holder.setData(room);
    }

    @Override
    public int getItemCount() {
        return userlist2.size();
    }

    public class RoomVH extends RecyclerView.ViewHolder {
        private TextView tvMess, tvDate, tvMess2, tvDate2;
        public RoomVH(View roomview) {
            super(roomview);
            tvMess = roomview.findViewById(R.id.textchata);
            tvDate = roomview.findViewById(R.id.timea);
            tvMess2 = roomview.findViewById(R.id.textchatb);
            tvDate2 = roomview.findViewById(R.id.timeb);
        }
        public void setData(Room room) {
            tvMess.setText(room.getTvMess());
            tvDate.setText(room.getTvDate());
            tvMess2.setText(room.getTvmess2());
            tvDate2.setText(room.getTvDate2());
        }
    }
}
