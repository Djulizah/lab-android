package com.example.assignment_6_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvPost = view.findViewById(R.id.rv_posts);
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPost.setHasFixedSize(true);

        PostAdapter adapter = new PostAdapter(getContext(), DataSource.posts);
        rvPost.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}