package com.arjun.halfride.Fragments;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arjun.halfride.HelperClass.HelperClass;
import com.arjun.halfride.HelperClass.model;
import com.arjun.halfride.HelperClass.requests;
import com.arjun.halfride.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class DescriptionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String iName, iLocation, iOrganisation, iDescription, iNumber, iEmail;
    String name, location, organisation, description, number, email;
    private DatabaseReference userDatabaseReference;

    private DatabaseReference friendRequestReference;
    private FirebaseAuth mAuth;
    private String CURRENT_STATE;
    HelperClass helperClass;

    public String receiver_userID; // Visited profile's id
    public String senderID, userKey; // Owner ID
    FirebaseDatabase database;
    DatabaseReference reference;
    Button ping;

    private DatabaseReference friendsDatabaseReference;
    private DatabaseReference notificationDatabaseReference;

    public DescriptionFragment() {

    }

    public DescriptionFragment(String name, String location, String organisation, String description, String userId, String number, String email) {
        this.name=name;
        this.location=location;
        this.organisation=organisation;
        this.description=description;
        this.receiver_userID=userId;
        this.number=number;
        this.email=email;

    }


    public static DescriptionFragment newInstance(String param1, String param2) {
        DescriptionFragment fragment = new DescriptionFragment();
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
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        TextView name1 = view.findViewById(R.id.qname);
        TextView location1 = view.findViewById(R.id.qlocation);
        TextView organisation1 = view.findViewById(R.id.qorganisation);
        TextView description1 = view.findViewById((R.id.qdescription));
        Button ping = view.findViewById(R.id.ping);

        name1.setText(name);
        location1.setText(location);
        organisation1.setText(organisation);
        description1.setText(description);


        userDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        notificationDatabaseReference = FirebaseDatabase.getInstance().getReference().child("notifications");

        senderID = mAuth.getCurrentUser().getUid();
        String userId = GoogleSignIn.getLastSignedInAccount(getActivity()).getId();

        CURRENT_STATE = "not_friends";

        FirebaseDatabase.getInstance().getReference().child("users")
                .child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        HelperClass helperClass = snapshot.getValue(HelperClass.class);

                        iName = snapshot.child("name").getValue().toString();
                        iNumber = snapshot.child("number").getValue().toString();
                        iEmail = snapshot.child("email").getValue().toString();
                        iLocation = snapshot.child("location").getValue().toString();
                        iOrganisation = snapshot.child("organisation").getValue().toString();

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

        if (!senderID.equals(receiver_userID)) {
            ping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CURRENT_STATE.equals("not_friends")) {
                        sendFriendRequest();

                    }
                }
            });
        }
        return view;
    }


    private void sendFriendRequest() {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("notifications");

        String from = senderID;
        String name = iName;
        String number = iNumber;
        String email = iEmail;
        String location  = iLocation;
        String organisation = iOrganisation;

        requests requests = new requests(from, name, number, email, location, organisation);
        reference.child(receiver_userID).setValue(requests)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            CURRENT_STATE = "request_sent";
                            Toast.makeText(getContext(), "Ping has been sent", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
