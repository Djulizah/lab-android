package com.example.tp5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SearchFragment extends Fragment {

    private SearchView svSeacrh;
    private ProgressBar progressBar;
    private RecyclerView rvSearch;
    private SearchAdapter searchAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        svSeacrh = view.findViewById(R.id.svSearch);
        progressBar = view.findViewById(R.id.progressBar);
        rvSearch = view.findViewById(R.id.rvSearch);
        searchAdapter = new SearchAdapter();
        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvSearch.setAdapter(searchAdapter);

        svSeacrh.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                progressBar.setVisibility(View.VISIBLE);
                rvSearch.setVisibility(View.GONE);

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                executor.execute(() -> {
                    try {
                        //simulate process in background thread
                            Thread.sleep(500);

                            handler.post(() -> {
                                //update ui in main thread
                                progressBar.setVisibility(View.GONE);
                                rvSearch.setVisibility(View.VISIBLE);


                            });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }});
                if (s.isEmpty()) {
                    searchAdapter.setListUser(new ArrayList<>());
                    return true;
                }
                searchAdapter.setListUser(HomeFragment.data.getUsersByQuery(s));
                searchAdapter.notifyDataSetChanged();
                return true;

            }
        });
    }
}