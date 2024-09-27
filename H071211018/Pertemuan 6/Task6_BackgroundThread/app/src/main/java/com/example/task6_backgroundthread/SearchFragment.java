package com.example.task6_backgroundthread;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {
    private ProgressBar progressBar;
    private TextInputEditText et_search;
    private RecyclerView rv_search;
    private SearchAdapter searchAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        et_search = view.findViewById(R.id.et_search);
        rv_search = view.findViewById(R.id.rv_search);

        searchAdapter = new SearchAdapter();
        rv_search.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_search.setAdapter(searchAdapter);



        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                progressBar.setVisibility(View.VISIBLE);
                rv_search.setVisibility(View.GONE);

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());

                executor.execute(() -> {
                    try {
                        Thread.sleep(500);



                        handler.post(() -> {
                            if (TextUtils.isEmpty(charSequence)) {
                                searchAdapter.clearListUser();
                            } else {
                                searchAdapter.setListUser(HomeFragment.dataSources.getUsersByQuery(charSequence.toString()));
                            }

                            searchAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            rv_search.setVisibility(View.VISIBLE);
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });


            }

            @Override
            public void afterTextChanged(Editable editable) {

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