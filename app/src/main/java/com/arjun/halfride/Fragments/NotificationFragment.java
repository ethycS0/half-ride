package com.arjun.halfride.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arjun.halfride.Adapter.MyAdapter;
import com.arjun.halfride.Adapter.rAdapter;
import com.arjun.halfride.HelperClass.HelperClass;
import com.arjun.halfride.HelperClass.model;
import com.arjun.halfride.HelperClass.requests;
import com.arjun.halfride.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class NotificationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String name, location, organisation, description, userId, number, email;
    DatabaseReference databaseReference;

    RecyclerView recycler;
    rAdapter adapter;

    public String user;
    private String mParam1;
    private String mParam2;


    public NotificationFragment() {
    }

    public NotificationFragment(String name, String location, String organisation, String description, String userId, String number, String email) {
        this.name=name;
        this.location=location;
        this.organisation=organisation;
        this.description=description;
        this.userId=userId;
        this.email=email;
        this.number=number;

    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.Recyclerreq);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));


        user = GoogleSignIn.getLastSignedInAccount(getActivity()).getId();

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("notifications"), model.class)
                        .build();

        adapter = new rAdapter(options);
        recycler.setAdapter(adapter);


        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}