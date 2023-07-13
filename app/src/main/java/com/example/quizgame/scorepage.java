package com.example.quizgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class scorepage extends AppCompatActivity {



    TextView scorecorrect, scorewrong;
    Button playagain, exit;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference1 = database.getReference().child("scores");

    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    String usercorrect;
    String userwrong;

    WebView webView;







    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorepage);


        scorecorrect = findViewById(R.id.correct);
        scorewrong = findViewById(R.id.wrong);
        playagain = findViewById(R.id.playagain);
        exit = findViewById(R.id.exit);


        webView = findViewById(R.id.webview);
        webView.loadUrl("https://editablegifs.com/gif/congratulations-gif/congratulations-2.gif");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());



        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String userUID = user.getUid();
                usercorrect = Objects.requireNonNull(snapshot.child(userUID).child("correct").getValue()).toString();
                userwrong = Objects.requireNonNull(snapshot.child(userUID).child("wrong").getValue()).toString();

                scorecorrect.setText(""+usercorrect);
                scorewrong.setText(""+userwrong);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        playagain.setOnClickListener(v -> {
            Intent i = new Intent(scorepage.this,signinpage.class);
            startActivity(i);
            finish();
        });

        exit.setOnClickListener(v -> finish());






    }


}