package com.example.notesappsqlite;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappsqlite.data.model.Note;

import java.util.ArrayList;

@SuppressLint("NotifyDataSetChanged")
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ClickListener clickListener;

    public NoteAdapter() {}

    private final ArrayList<Note> notes = new ArrayList<>();

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        // For searching purpose
        if (this.notes.size() > 0) this.notes.clear();

        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    public void addItem(Note note) {
        this.notes.add(note);
//        notifyItemInserted(this.notes.size() - 1);
    }

    public void updateItem(Note note, int position) {
        this.notes.set(position, note);
        notifyItemChanged(position, note);
    }

    public void removeItem(int position) {
        this.notes.remove(position);
        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, this.notes.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = notes.get(position);

        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        holder.tvDate.setText(note.getDate());

        holder.itemView.setOnClickListener(view -> clickListener.onItemClicked(note, position));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClicked(Note note, int position);
    }
}

