package com.example.assignment_6_1;

import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ProgressBar;
import android.widget.SearchView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {
    UserAdapter adapter;
    RecyclerView rvUser;
    SearchView searchView;
    ProgressBar progressBar;
    List<User> filteredList;
    Handler mHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //handling search view outline corners though there's no outline lmao
        searchView = view.findViewById(R.id.searchview);
        searchView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0, view.getWidth(), view.getHeight(), 10f);
            }
        });
        searchView.setClipToOutline(true);

        //progressBar
        progressBar = view.findViewById(R.id.progress_circular);

        //recyclerview
        rvUser = view.findViewById(R.id.rv_users);
        rvUser.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUser.setHasFixedSize(true);

        adapter = new UserAdapter(getContext(), DataSource.generateUsers());
        rvUser.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //bgThread Handle
        mHandler = new Handler();

        //searchview filtering
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });

                        for (int i = 0; i <= 10; i++) {
                            final int progress = i;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progress * 10);
                                }
                            });

                            // Simulate some work being done
//                            filterList(newText);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                filterList(newText);

                                if (TextUtils.isEmpty(newText)) {
                                    rvUser.setVisibility(View.GONE); // Hide the RecyclerView
                                } else {
                                    rvUser.setVisibility(View.VISIBLE); // Show the RecyclerView
                                    adapter.setFilteredList((ArrayList<User>) filteredList);
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });

//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (TextUtils.isEmpty(newText)) {
//                                    rvUser.setVisibility(View.GONE); // Hide the RecyclerView
//                                } else {
//                                    rvUser.setVisibility(View.VISIBLE); // Show the RecyclerView
//                                    adapter.setFilteredList((ArrayList<User>) filteredList);
//                                }
//                            }
//                        });
                    }
                }).start();

                return false;
            }

        });

    }
    private void filterList(String newText) {
        filteredList = new ArrayList<>();

        for (User user : DataSource.generateUsers()) {
            if (user.getName().toLowerCase().startsWith(newText.toLowerCase()) ||
                    user.getUsername().toLowerCase().startsWith(newText.toLowerCase())) {
                filteredList.add(user);
            }
        }

//        if (TextUtils.isEmpty(newText)) {
//            rvUser.setVisibility(View.GONE); // Hide the RecyclerView
//        } else {
//            rvUser.setVisibility(View.VISIBLE); // Show the RecyclerView
//            adapter.setFilteredList((ArrayList<User>) filteredList);
//        }

    }
}

// yg ku tangkap everytime we type (by 1 input either alphabets or space or deletes tab, the progressbar would show for about ~200 ish ms.
// and thenn show the recycler view. Based on previous trial Error advice is ti use AsyncTask class... BUT IDONT GET IT)
// also what the hell is an WeakReference WHAT!!!!