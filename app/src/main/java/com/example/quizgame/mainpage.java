package com.example.quizgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class mainpage extends AppCompatActivity {

    TextView time, correct, wrong;
    TextView question, a, b, c, d;
    Button finish, next;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference().child("Questions");

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    DatabaseReference databaseReferencesecond = database.getReference();

    String quizquestion;
    String quizoption1;
    String quizoption2;
    String quizoption3;
    String quizoption4;
    String quizcorrectanswer;

    int questioncount;

    int questionnumber = 1;

    String useranswer;

    int usercorrect = 0;
    int userwrong = 0;

    CountDownTimer countDownTimer;
    private static final long TOTAL_TIME = 25000;
    Boolean timerContinue;
    long leftTime = TOTAL_TIME;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        time = findViewById(R.id.time);
        correct = findViewById(R.id.correct);
        wrong = findViewById(R.id.wrong);

        question = findViewById(R.id.question);
        a = findViewById(R.id.option1);
        b = findViewById(R.id.option2);
        c = findViewById(R.id.option3);
        d = findViewById(R.id.option4);

        finish = findViewById(R.id.finish);
        next = findViewById(R.id.next);

        game();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendscore();
                Intent i = new Intent(mainpage.this,scorepage.class);
                startActivity(i);
                finish();
            }
        });





        next.setOnClickListener(v -> {

            resetTimer();

            game();



        });

        a.setOnClickListener(v -> {

            pauseTimer();

            useranswer = "a";

            if(useranswer.equals(quizcorrectanswer)){
                a.setBackgroundColor(Color.GREEN);
                usercorrect++;
                correct.setText(""+usercorrect);


            }
            else {
                a.setBackgroundColor(Color.RED);
                userwrong++;
                wrong.setText(""+userwrong);
                findanswer();

            }


        });

        b.setOnClickListener(v -> {

            pauseTimer();


            useranswer = "b";

            if(useranswer.equals(quizcorrectanswer)){
                b.setBackgroundColor(Color.GREEN);
                usercorrect++;
                correct.setText(""+usercorrect);


            }
            else {
                b.setBackgroundColor(Color.RED);
                userwrong++;
                wrong.setText(""+userwrong);

                findanswer();


            }



        });

        c.setOnClickListener(v -> {

            pauseTimer();


            useranswer = "c";

            if(useranswer.equals(quizcorrectanswer)){
                c.setBackgroundColor(Color.GREEN);
                usercorrect++;
                correct.setText(""+usercorrect);


            }
            else {
                c.setBackgroundColor(Color.RED);
                userwrong++;
                wrong.setText(""+userwrong);

                findanswer();


            }



        });

        d.setOnClickListener(v -> {

            pauseTimer();


            useranswer = "d";

            if(useranswer.equals(quizcorrectanswer)){
                d.setBackgroundColor(Color.GREEN);
                usercorrect++;
                correct.setText(""+usercorrect);


            }
            else {
                d.setBackgroundColor(Color.RED);
                userwrong++;
                wrong.setText(""+userwrong);

                findanswer();


            }



        });







    }

    public void game(){

        startTimer();

        a.setBackgroundColor(Color.WHITE);
        b.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
        d.setBackgroundColor(Color.WHITE);




        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                questioncount = (int) snapshot.getChildrenCount();


                quizquestion = snapshot.child(String.valueOf(questionnumber)).child("q").getValue().toString();
                quizoption1 = snapshot.child(String.valueOf(questionnumber)).child("a").getValue().toString();
                quizoption2 = snapshot.child(String.valueOf(questionnumber)).child("b").getValue().toString();
                quizoption3 = snapshot.child(String.valueOf(questionnumber)).child("c").getValue().toString();
                quizoption4 = snapshot.child(String.valueOf(questionnumber)).child("d").getValue().toString();
                quizcorrectanswer = snapshot.child(String.valueOf(questionnumber)).child("answer").getValue().toString();




                question.setText(quizquestion);
                a.setText(quizoption1);
                b.setText(quizoption2);
                c.setText(quizoption3);

                d.setText(quizoption4);

                if(questionnumber < questioncount){
                    questionnumber++;
                }
                else {
                    Toast.makeText(mainpage.this, "You answered all questions.", Toast.LENGTH_SHORT).show();
                }








            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mainpage.this, "There is an Error here ! Try again", Toast.LENGTH_SHORT).show();


            }
        });


}

public void findanswer(){

    switch (quizcorrectanswer) {
        case "a":
            a.setBackgroundColor(Color.GREEN);
            break;
        case "b":
            b.setBackgroundColor(Color.GREEN);
            break;
        case "c":
            c.setBackgroundColor(Color.GREEN);
            break;
        case "d":
            d.setBackgroundColor(Color.GREEN);
            break;
    }


}

public void startTimer()
{
    countDownTimer = new CountDownTimer(leftTime,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            leftTime = millisUntilFinished;
            updateCountDownText();

        }

        @Override
        public void onFinish() {

            timerContinue = false;
            pauseTimer();
            question.setText("Sorry, Time is Up !");

        }
    }.start();

    timerContinue = true;


}

public void resetTimer()
{
    leftTime = TOTAL_TIME;
    updateCountDownText();



}

public void  updateCountDownText()
{
    int second = (int)(leftTime/1000)%60;
    time.setText(""+second);


}

public void pauseTimer(){

        countDownTimer.cancel();
        timerContinue = false;

}

public void sendscore()
{

    String userUID = user.getUid();
    databaseReferencesecond.child("scores").child(userUID).child("correct").setValue(usercorrect)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(mainpage.this,"score sent successfully",Toast.LENGTH_SHORT).show();
                        }
                    });
    databaseReferencesecond.child("scores").child(userUID).child("wrong").setValue(userwrong);






}





}
