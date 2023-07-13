package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signinpage extends AppCompatActivity {

    Button letsplay;
    TextView signouttextview;

    FirebaseAuth auth = FirebaseAuth.getInstance();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinpage);

        letsplay = findViewById(R.id.letsplaybutton);
        signouttextview = findViewById(R.id.signouttextview);

        letsplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signinpage.this,mainpage.class);
                startActivity(i);


            }
        });

        signouttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                Toast.makeText(signinpage.this,"SIGN OUT SUCCESFULLY ! ",Toast.LENGTH_SHORT).show();


                Intent i = new Intent(signinpage.this, loginactivity.class);
                startActivity(i);
                finish();



            }
        });

    }
}