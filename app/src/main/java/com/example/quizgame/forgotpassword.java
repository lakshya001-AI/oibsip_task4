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
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    EditText emailtext;
    Button forgot;

    FirebaseAuth auth = FirebaseAuth.getInstance();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        emailtext = findViewById(R.id.emaildittext2);
        forgot = findViewById(R.id.forgotbutton);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailtext.getText().toString();
                forgot(email);



            }
        });

    }

    public void forgot(String email){

        auth.sendPasswordResetEmail(email).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    Toast.makeText(forgotpassword.this,"Resetpassword Link Sent",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(forgotpassword.this,loginactivity.class);
                    startActivity(i);
                    finish();

                }

                else {

                    Toast.makeText(forgotpassword.this," FAILED ! Resetpassword Link Not Sent",Toast.LENGTH_SHORT).show();




                }

            }
        });



    }
}