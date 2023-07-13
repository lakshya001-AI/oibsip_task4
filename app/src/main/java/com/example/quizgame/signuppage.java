package com.example.quizgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signuppage extends AppCompatActivity {

    EditText emailedittext;
    EditText passwordedittext;
    Button signup;

    FirebaseAuth auth = FirebaseAuth.getInstance();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        emailedittext = findViewById(R.id.signupemail);
        passwordedittext = findViewById(R.id.signuppassword);
        signup = findViewById(R.id.signupbutton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailedittext.getText().toString();
                String password = emailedittext.getText().toString();

                signup(email,password);






            }
        });





    }

    public void signup(String email, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(signuppage.this,"ACCOUNT CREATED SUCCESFULLY",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signuppage.this,loginactivity.class);
                            startActivity(i);
                            finish();



                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(signuppage.this,"SORRY ! TRY AGAIN ",Toast.LENGTH_SHORT).show();




                        }
                    }
                });



    }


}