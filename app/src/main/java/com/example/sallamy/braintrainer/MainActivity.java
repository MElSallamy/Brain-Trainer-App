package com.example.sallamy.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button play;
    Button bu1;
    Button bu2;
    Button bu3;
    Button bu4;
    TextView correct;
    TextView overall;
    TextView counterdountimer;
    TextView sum;
    int locationofcorrectamswer;
    int correctanswer;
    int incorrectanswer;
    int score = 0;
    int all = 0;
    int a;
    int b;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    // Start Button OnClick method
    public void start(View view) {

        // make Visibility invisible
        play.setVisibility(View.INVISIBLE);

        // make Buttons of choose the answer visible
        bu1.setVisibility(View.VISIBLE);
        bu2.setVisibility(View.VISIBLE);
        bu3.setVisibility(View.VISIBLE);
        bu4.setVisibility(View.VISIBLE);

        // set counter TestView 30s
        counterdountimer.setText("30s");

        // set text for the result TextView
        correct.setText("Do your best ! ");

        // generate the first question
        generate();

        // start time down timer
        timermethod();
    }


    // generate questions method
    public void generate() {
        // clear arr list
        answers.clear();

        Random random = new Random();
        // creating 2 random integers
        a = random.nextInt(21);
        b = random.nextInt(21);
        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));
        // creating 4 random int to select random Button to rut the correct answer in it
        locationofcorrectamswer = random.nextInt(4);
        correctanswer = a + b;
        // for loop to create random answers
        for (int x = 0; x < 4; x++) {
            if (x == locationofcorrectamswer) {
                // add the correct answer in arr list
                answers.add(correctanswer);
            } else {
                // add wrong answers in arr list
                incorrectanswer = random.nextInt(41);
                while (incorrectanswer == correctanswer) {
                    incorrectanswer = random.nextInt(41);
                }
                answers.add(incorrectanswer);
            }
        }
        // add the answers from arr list into Buttons
        bu1.setText(Integer.toString(answers.get(0)));
        bu2.setText(Integer.toString(answers.get(1)));
        bu3.setText(Integer.toString(answers.get(2)));
        bu4.setText(Integer.toString(answers.get(3)));
    }


    // Buttons visibility method
    public void buttons() {
        play.setVisibility(View.VISIBLE);
        bu1.setVisibility(View.INVISIBLE);
        bu2.setVisibility(View.INVISIBLE);
        bu3.setVisibility(View.INVISIBLE);
        bu4.setVisibility(View.INVISIBLE);
    }

    // CountDownTimer method
    public void timermethod() {
        // creating new CountDownTimer from 30s to 0s
        new CountDownTimer(30100, 1000) {
            @Override // every second set text with new time
            public void onTick(long millisUntilFinished) {
                counterdountimer.setText(Integer.toString((int) (millisUntilFinished / 1000)) + "s");
            }

            @Override // when the time finish method
            public void onFinish() {
                // write the total score in textview called correct
                correct.setText("your score : " + Integer.toString(score) + " / " + Integer.toString(all));
                // make the Button of start visible again and hide others
                buttons();
                // set text in it paly again !
                play.setText("play again !");
                // make the score and total score zero again
                score = 0;
                all = 0;
                overall.setText(Integer.toString(score) + " / " + Integer.toString(all));
                // play sound means time finished
                MediaPlayer winning = MediaPlayer.create(getApplicationContext(), R.raw.resultsound);
                winning.start();
            }
        }.start();
    }


    // Choosing the answer Click Button method
    public void choose(View view) {
        // check if answer is correct or not !
        if (view.getTag().toString().equals(Integer.toString(locationofcorrectamswer))) {
            correct.setText("correct ! ");
            score++;
        } else {
            correct.setText("wrong !");
        }
        // adding the score into TextView which called overall
        all++;
        overall.setText(Integer.toString(score) + " / " + Integer.toString(all));
        // after answering the Q generate new Q
        generate();
    }

    // onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Buttons
        play = (Button) findViewById(R.id.startButton);
        bu1 = (Button) findViewById(R.id.button1);
        bu2 = (Button) findViewById(R.id.button2);
        bu3 = (Button) findViewById(R.id.button3);
        bu4 = (Button) findViewById(R.id.button4);
        // TextViews
        correct = (TextView) findViewById(R.id.correctTextview);
        overall = (TextView) findViewById(R.id.pointTextView);
        sum = (TextView) findViewById(R.id.sumTextView);
        counterdountimer = (TextView) findViewById(R.id.timeTextView);
        // Buttons method to hide all buttons except the start button
        buttons();
    }
}