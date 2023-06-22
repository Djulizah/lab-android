package com.example.assignment_8_1;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.database.CursorWindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    ArrayList<NoteModel> noteModelArrayList;
    ClickListener clickListener;
    Context context;
    public NoteAdapter(Context context, ArrayList<NoteModel> noteModelArrayList){
        this.context = context;
        this.noteModelArrayList = noteModelArrayList;
    }
    void addNote(NoteModel note){
        noteModelArrayList.add(note);
    }

    void setItem(ArrayList<NoteModel> noteModels) {
        noteModelArrayList.clear();
        noteModelArrayList.addAll(noteModels);
        notifyDataSetChanged();
    }

    void updateNote(NoteModel noteModel, int index){
//        this.noteModelArrayList.remove(index);
//        this.noteModelArrayList.add(index, noteModel);
        noteModelArrayList.set(index, noteModel);
        notifyItemChanged(index);
    }

    void deleteNote(int index) {
//        NoteModel deletedNote = noteModelArrayList.get(index);

        noteModelArrayList.remove(index);
        notifyItemRemoved(index);

        notifyDataSetChanged();

//        // Delete the note from the database
//        NoteHelper noteHelper = NoteHelper.getInstance(context);
//        noteHelper.open();
//        noteHelper.deleteById(String.valueOf(deletedNote.getId()));
//        noteHelper.close();
//
//        int previousPosition = index;
//        this.noteModelArrayList.remove(index);
//        notifyItemRemoved(index);
//
//        // Update the position if the deleted note was before the current position
//        if (previousPosition > index) {
//            index--;
//        }
    }

    public NoteAdapter(ArrayList<NoteModel> noteModelArrayList) {
        this.noteModelArrayList = noteModelArrayList;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        NoteModel noteModel = noteModelArrayList.get(position);
        holder.setData(noteModel, position);
    }

    @Override
    public int getItemCount() {
        return noteModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCreatedAt, tvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreatedAt = itemView.findViewById(R.id.tv_created_at);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }

        public void setData(NoteModel noteModel, int pos) {
            tvTitle.setText(noteModel.getTitle());
            tvCreatedAt.setText(noteModel.getCreatedAt());
            tvDescription.setText(noteModel.getDescription());
            itemView.setOnClickListener(view -> {
                clickListener.onItemClicked(noteModel, pos);
            });
        }
    }

    interface ClickListener {
        void onItemClicked(NoteModel noteModel, int index);
    }
}

