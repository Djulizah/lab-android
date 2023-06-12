package com.example.tugas9.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tugas9.R;
import com.example.tugas9.models.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private ArrayList<Note> notes;

    private OnItemClickCallback onItemClickCallback;

    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.onBind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription, tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_desc);
            tvDate = itemView.findViewById(R.id.tv_created_at);
        }

        public void onBind(final Note note) {
            tvTitle.setText(note.getTitle());
            tvDescription.setText(note.getDescription());
            if (note.getEdited()) {
                tvDate.setText("Edited at " +  note.getDate());
            } else {
                tvDate.setText("Created at " +  note.getDate());
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickCallback.onItemClicked(note);
                }
            });
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Note note);
    }}
