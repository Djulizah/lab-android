package com.example.fragmenttask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SearchFragment extends Fragment {

    private SearchView svSearch;
    private RecyclerView rvChat;
   private ProgressBar progressBar;
   private SearchAdapter searchAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        svSearch = view.findViewById(R.id.sv_Search);
        rvChat = view.findViewById(R.id.rv_chat);
        progressBar = view.findViewById(R.id.progressBar);

        searchAdapter = new SearchAdapter();
        rvChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChat.setAdapter(searchAdapter);

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                progressBar.setVisibility(View.VISIBLE);
                rvChat.setVisibility(View.GONE);


                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());


                executor.execute(() -> {
                    try {
                        //simulate process in background thread
                        Thread.sleep(500);

                        handler.post(() -> {
                            //update ui in main thread
                            progressBar.setVisibility(View.GONE);
                            rvChat.setVisibility(View.VISIBLE);

                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }});

                if (newText.isEmpty()) {
                    searchAdapter.clearListUser();
                }else {
                    searchAdapter.setListuser(HomeFragment.datasource.getUsersByQuery(newText));
                }

                searchAdapter.notifyDataSetChanged();
                return false;

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}