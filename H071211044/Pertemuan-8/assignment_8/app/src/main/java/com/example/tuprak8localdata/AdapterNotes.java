package com.example.tuprak8localdata;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.ViewHolder> {

    private ArrayList<Notes> dataitem = new ArrayList<>();

    public void setData(ArrayList<Notes> notesList) {
        dataitem.clear();
        if (notesList != null) {
            dataitem.addAll(notesList);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterNotes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotes.ViewHolder holder, int position) {
        Notes notes = dataitem.get(position);
        holder.tvTittle.setText(notes.getTitle());
        holder.tvDesc.setText(notes.getDescription());
        holder.tvCreatedAT.setText(notes.getCreatedAt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MainActivity2.class);
                if (notes != null){
                    intent.putExtra(MainActivity2.EXTRA_STUDENT, notes);
                }
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTittle, tvCreatedAT, tvDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTittle = itemView.findViewById(R.id.tv_title);
            tvCreatedAT = itemView.findViewById(R.id.tv_createdAt);
            tvDesc = itemView.findViewById(R.id.tv_desc);

        }
    }
}
