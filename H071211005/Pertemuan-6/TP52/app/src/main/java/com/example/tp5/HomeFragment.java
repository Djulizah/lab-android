package com.example.tp5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    static final DataSource data = new DataSource();
    static final postAdapter adapter = new postAdapter(data.getPosts());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvEmpty = view.findViewById(R.id.tvEmpty);
        RecyclerView rvPost = view.findViewById(R.id.rvPost);
        rvPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPost.setAdapter(adapter);

        if(adapter.getItemCount() > 0) {
            tvEmpty.setVisibility(View.GONE);
        }
    }
}