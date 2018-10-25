package com.ytl.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView textView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scollTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        playAgainButton.setVisibility(View.INVISIBLE);
        timerTextView.setText("30s");
        resultTextView.setText("");
        scollTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestions();

        CountDownTimer countDownTimer = new CountDownTimer(30200, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l /1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void chooseAnswer(View view){

        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            Log.i("Correct!", "you got it");
            resultTextView.setText("Corect!");
            score++;
        } else {
            Log.i("wrong", ":(");
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scollTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        Log.i("tag",view.getTag().toString());
        newQuestions();
    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestions(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        textView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for(int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.sumTextView);
        goButton = findViewById(R.id.goButton);
        resultTextView = findViewById(R.id.resultTextView);
        scollTextView = findViewById(R.id.scollTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        gameLayout = findViewById(R.id.gameLayout);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        playAgain(timerTextView);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
