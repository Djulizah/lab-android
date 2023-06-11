package com.example.inigaram.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.inigaram.R;
import com.example.inigaram.adapter.SearchAdapter;
import com.example.inigaram.datasources.UserDataSource;
import com.example.inigaram.model.User;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private SearchAdapter searchAdapter;
    private ArrayList<User> filteredUsers = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper());
    private Thread searchThread;
    private Runnable searchRunnable;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editText = view.findViewById(R.id.search_edit_text);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.result_recycler_view);

        searchAdapter = new SearchAdapter(getActivity(), filteredUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(searchAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                progressBar.setVisibility(View.VISIBLE);
                filteredUsers.clear();

                if (searchRunnable != null) {
                    handler.removeCallbacks(searchRunnable);
                }

                searchRunnable = new Runnable() {
                    @Override
                    public void run() {
                        searchUsers(s.toString());
                    }
                };

                handler.postDelayed(searchRunnable, 200);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void searchUsers(final String keyword) {
        if (searchThread != null && searchThread.isAlive()) {
            searchThread.interrupt();
        }

        if (keyword.isEmpty()) {
            filteredUsers.clear();
            updateUsersList(filteredUsers);
            return;
        }

        searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (User user : UserDataSource.getUserArrayList()) {
                    if (user.getFullName().toLowerCase().startsWith(keyword.toLowerCase()) ||
                            user.getUserName().toLowerCase().startsWith(keyword.toLowerCase())) {
                        filteredUsers.add(user);
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateUsersList(filteredUsers);
                    }
                });
            }
        });

        searchThread.start();
    }

    private void updateUsersList(ArrayList<User> users) {
        progressBar.setVisibility(View.GONE);
        searchAdapter.setUsers(users);
        searchAdapter.notifyDataSetChanged();
    }
}
