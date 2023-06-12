package com.example.tugas7fragment;

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

import com.example.tugas7fragment.models.User;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    static final PostAdapter adapter = new PostAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvEmptyPosts = view.findViewById(R.id.tv_empty_posts);
        RecyclerView rvPost = view.findViewById(R.id.rv_post);

        rvPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvPost.setAdapter(adapter);


        if (adapter.getItemCount() > 0) {
            tvEmptyPosts.setVisibility(View.GONE);
        }
    }
}