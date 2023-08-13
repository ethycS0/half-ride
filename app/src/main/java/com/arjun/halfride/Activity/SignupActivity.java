package com.arjun.halfride.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.arjun.halfride.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    EditText signupNumber;
    TextInputLayout til_location, til_organisation;
    AutoCompleteTextView acl_location, acl_organisation;
    ArrayList<String> arrayList_location, arrayList_organisation;
    ArrayAdapter<String> arrayAdapter_location, arrayAdapter_organisation;
    Button signupButton;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient mSignInClient;
    FirebaseAuth firebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent i = new Intent(SignupActivity.this, StartActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }

        firebaseAuth = FirebaseAuth.getInstance();
        signupNumber = (EditText) findViewById(R.id.signup_number);
        signupButton = (Button) findViewById(R.id.signup_button);


        til_location=(TextInputLayout) findViewById(R.id.til_location);
        acl_location=(AutoCompleteTextView) findViewById(R.id.acl_location);

        arrayList_location=new ArrayList<>();
        arrayList_location.add(" 9 Drd");
        arrayList_location.add(" A.r. Shala");
        arrayList_location.add(" Afmc");
        arrayList_location.add(" Airport (pune)");
        arrayList_location.add(" Akurdi");
        arrayList_location.add(" Ammunition Factory Khadki");
        arrayList_location.add(" Anandnagar (pune)");
        arrayList_location.add(" Armament");
        arrayList_location.add(" Aundh T.s.");
        arrayList_location.add(" Bajirao Road ");
        arrayList_location.add(" Baner Road");
        arrayList_location.add(" Bhavani Peth");
        arrayList_location.add(" Bhosari I.e.");
        arrayList_location.add(" Bhosarigoan");
        arrayList_location.add(" Bhusari Colony");
        arrayList_location.add(" Bibvewadi");
        arrayList_location.add(" Botanical Garden (pune)");
        arrayList_location.add(" C D A (o)");
        arrayList_location.add(" C M E");
        arrayList_location.add(" Chinchwad East ");
        arrayList_location.add(" Chinchwadgaon");
        arrayList_location.add(" Congress House Road");
        arrayList_location.add(" Dapodi Bazar");
        arrayList_location.add(" Dapodi");
        arrayList_location.add(" Deccan Gymkhana");
        arrayList_location.add(" Dhankawadi");
        arrayList_location.add(" Dhanori");
        arrayList_location.add(" Dighi Camp");
        arrayList_location.add(" Dr.b.a. Chowk");
        arrayList_location.add(" Dukirkline");
        arrayList_location.add(" East Khadki");
        arrayList_location.add(" Ex. Serviceman Colony");
        arrayList_location.add(" Film Institute");
        arrayList_location.add(" Ganeshkhind");
        arrayList_location.add(" Ghorpade Peth");
        arrayList_location.add(" Ghorpuri Bazar");
        arrayList_location.add(" Govt. Polytechnic");
        arrayList_location.add(" Guruwar Peth");
        arrayList_location.add(" H.e. Factory");
        arrayList_location.add(" Hadapsar");
        arrayList_location.add(" Hadpsar I.e.");
        arrayList_location.add(" Iaf Station");
        arrayList_location.add(" Indrayaninagar");
        arrayList_location.add(" Kalewadi");
        arrayList_location.add(" Kapad Ganj");
        arrayList_location.add(" Karvenagar");
        arrayList_location.add(" Kasarwadi");
        arrayList_location.add(" Kasba Peth");
        arrayList_location.add(" Katraj");
        arrayList_location.add(" Khadakwasla R.s.");
        arrayList_location.add(" Khadki Bazar");
        arrayList_location.add(" Khadki");
        arrayList_location.add(" Khondhwa Kh");
        arrayList_location.add(" Kondhwa Bk");
        arrayList_location.add(" Kondhwa Lh");
        arrayList_location.add(" Kothrud");
        arrayList_location.add(" Lohogaon");
        arrayList_location.add(" Lokmanyanagar");
        arrayList_location.add(" M.phulenagar");
        arrayList_location.add(" Magarpatta City");
        arrayList_location.add(" Mangalwar Peth (pune)");
        arrayList_location.add(" Market Yard (pune)");
        arrayList_location.add(" Masulkar Colony");
        arrayList_location.add(" Model Colony");
        arrayList_location.add(" Mohamadwadi");
        arrayList_location.add(" Mundhva Av");
        arrayList_location.add(" Mundhva");
        arrayList_location.add(" N I B M");
        arrayList_location.add(" N.c.l. Pune");
        arrayList_location.add(" N.d.a. Khadakwasla");
        arrayList_location.add(" N.i.a.");
        arrayList_location.add(" N.w. College");
        arrayList_location.add(" Nana Peth");
        arrayList_location.add(" Narayan Peth");
        arrayList_location.add(" Navsahyadri");
        arrayList_location.add(" Nehrunagar (pune)");
        arrayList_location.add(" P.c.n.t.");
        arrayList_location.add(" Parvati Gaon");
        arrayList_location.add(" Parvati");
        arrayList_location.add(" Pimpri Colony");
        arrayList_location.add(" Pimpri P F");
        arrayList_location.add(" Pimpri Waghire");
        arrayList_location.add(" Punawale");
        arrayList_location.add(" Pune Cantt East");
        arrayList_location.add(" Pune City");
        arrayList_location.add(" Pune");
        arrayList_location.add(" Pune New Bazar");
        arrayList_location.add(" Range Hills");
        arrayList_location.add(" Rashtra Bhasha Bhavan");
        arrayList_location.add(" Rasta Peth");
        arrayList_location.add(" Raviwar Peth");
        arrayList_location.add(" Rupeenagar");
        arrayList_location.add(" S.p. College");
        arrayList_location.add(" S.s.c.exam Board");
        arrayList_location.add(" Sachapir Street");
        arrayList_location.add(" Sadashiv Peth");
        arrayList_location.add(" Salisbury Park");
        arrayList_location.add(" Sangavi");
        arrayList_location.add(" Sasanenagar");
        arrayList_location.add(" Shaniwar Peth (pune)");
        arrayList_location.add(" Shivaji Housing Society");
        arrayList_location.add(" Shivajinagar (pune)");
        arrayList_location.add(" Shukrawar Peth (pune)");
        arrayList_location.add(" Srpf");
        arrayList_location.add(" Swargate Chowk");
        arrayList_location.add(" Swargate");
        arrayList_location.add(" T.v. Nagar");
        arrayList_location.add(" Talwade");
        arrayList_location.add(" Thathawade");
        arrayList_location.add(" Thergaon");
        arrayList_location.add(" Vadgaon Budruk");
        arrayList_location.add(" Vadgaon Sheri");
        arrayList_location.add(" Vidyanagar (pune)");
        arrayList_location.add(" Vishrantwadi");
        arrayList_location.add(" Wakad");
        arrayList_location.add(" Wanowarie");
        arrayList_location.add(" Warje");
        arrayList_location.add(" Yamunanagar");
        arrayList_location.add(" Yerwada");
        arrayList_location.add(" Yerwada T.s. ");

        arrayAdapter_location=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList_location);
        acl_location.setAdapter(arrayAdapter_location);
        acl_location.setThreshold(1);


        til_organisation=(TextInputLayout) findViewById(R.id.til_organisation);
        acl_organisation=(AutoCompleteTextView) findViewById(R.id.act_organisation);
        arrayList_organisation=new ArrayList<>();
        arrayList_organisation.add(" Ajeenkya DY Patil SOE");

        arrayAdapter_organisation=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList_organisation);
        acl_organisation.setAdapter((arrayAdapter_organisation));
        acl_organisation.setThreshold(1);

        /*String rLocation = acl_location.getText().toString();
        String rOrganisation = acl_organisation.getText().toString();*/


        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("897601855677-v0voliso9djmq6fd7p4rfdb7arp7sml7.apps.googleusercontent.com").requestEmail().build();

        mSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = signupNumber.getText().toString().trim();
                if (phoneNumber.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please enter your Phone Number", Toast.LENGTH_SHORT).show();

                    /*} else if(rOrganisation.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Please enter your Organisation", Toast.LENGTH_SHORT).show();

                    }   else if(rLocation.isEmpty()){

                        Toast.makeText(getApplicationContext(), "Please enter your Location", Toast.LENGTH_SHORT).show();*/

                    } else {

                        SignIn();

                }
            }
        });
    }

    private void SignIn() {

        Intent intent = mSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (googleSignInAccountTask.isSuccessful()) {
                try {
                    GoogleSignInAccount googleSignInAccount = googleSignInAccountTask.getResult(ApiException.class);

                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference().child("users");
                                    HashMap<String, String> user_details = new HashMap<>();


                                    String id = googleSignInAccount.getId().toString();
                                    String name = googleSignInAccount.getDisplayName().toString();
                                    String email = googleSignInAccount.getEmail().toString();
                                    String number = signupNumber.getText().toString().trim();
                                    String location = acl_location.getText().toString().trim();
                                    String organisation = acl_organisation.getText().toString().trim();


                                    user_details.put("userId", id);
                                    user_details.put("name", name);
                                    user_details.put("email", email);
                                    user_details.put("number", number);
                                    user_details.put("location", location);
                                    user_details.put("organisation", organisation);


                                    myRef.child(id).setValue(user_details).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}