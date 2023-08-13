package com.arjun.halfride.Fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arjun.halfride.Activity.EditProfileActivity;
import com.arjun.halfride.Activity.SignupActivity;
import com.arjun.halfride.HelperClass.HelperClass;
import com.arjun.halfride.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment {

    TextView uName, uNumber, uEmail, uLocation, uOrganisation, uDescription;
    Button editDetailsBtn, signOutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        String userId = GoogleSignIn.getLastSignedInAccount(getActivity()).getId();

        uNumber = (TextView) view.findViewById(R.id.number);
        uName = (TextView) view.findViewById(R.id.name);
        uEmail = (TextView) view.findViewById(R.id.email);
        uLocation = (TextView) view.findViewById(R.id.location);
        uOrganisation = (TextView) view.findViewById(R.id.organisation);
        uDescription = (TextView) view.findViewById(R.id.description);
        editDetailsBtn = (Button) view.findViewById(R.id.manageProfile);
        signOutBtn = (Button) view.findViewById(R.id.logout_button);

        editDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);

            }
        });

        FirebaseDatabase.getInstance().getReference().child("users")
                .child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        HelperClass helperClass = snapshot.getValue(HelperClass.class);

                        uName.setText(helperClass.getName());
                        uNumber.setText(helperClass.getNumber());
                        uEmail.setText(helperClass.getEmail());
                        uLocation.setText(helperClass.getLocation());
                        uOrganisation.setText(helperClass.getOrganisation());
                        uDescription.setText(helperClass.getDescription());

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GoogleSignInOptions gso = new GoogleSignInOptions.
                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                        build();

                GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().signOut();

                            Intent intent = new Intent(getContext(), SignupActivity.class);

                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }

                    }
                });


            }
        });

        return view;
    }
}