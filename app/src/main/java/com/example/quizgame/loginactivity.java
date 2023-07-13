package com.example.quizgame;

//      In this project we will be going to use the FIREBASE DATABASE     // 

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginactivity extends AppCompatActivity {

    EditText emailedittext;
    EditText passwordedittext;
    Button signinbutton;
    ImageView googlesignin;
    TextView signup;
    TextView forgotpassword;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    GoogleSignInClient googleSignInClient;

    ActivityResultLauncher<Intent> activityResultLauncher;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        emailedittext = findViewById(R.id.emailedittext);
        passwordedittext = findViewById(R.id.passworddittext);
        signinbutton = findViewById(R.id.signinbutton);
        googlesignin = findViewById(R.id.googlesignin);
        signup = findViewById(R.id.signuptext);
        forgotpassword = findViewById(R.id.forgotpasswordtext);

        registerActivityForGoogleSignIn();





        googlesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signinGoogle();





            }
        });






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginactivity.this,signuppage.class);
                startActivity(i);
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginactivity.this, com.example.quizgame.forgotpassword.class);
                startActivity(i);

            }
        });

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailedittext.getText().toString();
                String password = passwordedittext.getText().toString();


                signin(email, password);



            }











            });


    }

    public void registerActivityForGoogleSignIn(){

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                int resultcode = result.getResultCode();
                Intent data = result.getData();

                if(resultcode == RESULT_OK && data != null){

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    firebaseSignInwithGoogle(task);

                }

            }
        });



    }

    private void firebaseSignInwithGoogle(Task<GoogleSignInAccount> task){

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(loginactivity.this,"SUCCESSFUL ! SIGNED IN",Toast.LENGTH_SHORT).show();

            Intent i = new Intent(loginactivity.this,signinpage.class);
            startActivity(i);
            finish();

            firebaseGoogleAccount(account);




        } catch (ApiException e) {
            e.printStackTrace();

            Toast.makeText(loginactivity.this,"ERROR! TRY AGAIN LATER",Toast.LENGTH_SHORT).show();


        }

    }

    private void firebaseGoogleAccount(GoogleSignInAccount account){

        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        auth.signInWithCredential(authCredential).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                           // FirebaseUser user = auth.getCurrentUser();


                        }
                        else {

                        }

                    }
                });

    }



    public void signinGoogle(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestIdToken("296856773640-2maft6f5kjo5p97nsstdtpcc0t2bpllo.apps.googleusercontent.com")
                .requestEmail().build();


        googleSignInClient = GoogleSignIn.getClient(this,gso);

        signin();


    }

    public void signin(){

        Intent signinIntent = googleSignInClient.getSignInIntent();

        activityResultLauncher.launch(signinIntent);



    }



    public void signin(String email, String password ){

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(loginactivity.this,signinpage.class);
                            startActivity(intent);

                            Toast.makeText(loginactivity.this,"SUCCESFULLY ! SIGNED IN ",Toast.LENGTH_SHORT).show();


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(loginactivity.this,"WRONG CREDENTIAL'S ! TRY AGAIN ",Toast.LENGTH_SHORT).show();


                        }
                    }
                });





    }








}