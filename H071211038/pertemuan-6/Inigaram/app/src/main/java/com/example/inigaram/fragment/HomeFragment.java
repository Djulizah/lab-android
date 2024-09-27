package com.example.inigaram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.inigaram.R;
import com.example.inigaram.adapter.HomeAdapter;
import com.example.inigaram.datasources.PostDataSource;
import com.example.inigaram.model.Post;

public class HomeFragment extends Fragment {
    private static ArrayList<Post> postArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.text_view);
        RecyclerView postRecyclerView = view.findViewById(R.id.recycler_view);

        postArrayList = PostDataSource.getPostList();
        HomeAdapter homeAdapter = new HomeAdapter(postArrayList, getActivity());

        if (postArrayList != null && !postArrayList.isEmpty()) {
            textView.setVisibility(View.GONE);
            postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            postRecyclerView.setAdapter(homeAdapter);
        }
    }

}