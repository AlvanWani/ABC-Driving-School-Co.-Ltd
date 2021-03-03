 package com.example.abc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

 public class Quizs extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";

    private TextView textViewQuestion,textViewScore,textViewQuestionCountDown,textViewQuestionCount;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3;
    private Button mBtnConfirmNext;

    private ColorStateList textColorDefualtRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCounterTotal;
    private  Question currentQuestion;

     private int score;
     private boolean answered;

     private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizs);

        textViewQuestion = findViewById(R.id.tv_qtns);
        textViewScore = findViewById(R.id.tv_score);
        textViewQuestionCountDown = findViewById(R.id.tv_count_down);
        textViewQuestionCount = findViewById(R.id.tv_qns_count);
        radioGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_btn1);
        rb2 = findViewById(R.id.radio_btn2);
        rb3 = findViewById(R.id.radio_btn3);
        mBtnConfirmNext = findViewById(R.id.btn_confirm_next);

         textColorDefualtRb = rb1.getTextColors();

        QuizDbHelper dbHelper =new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCounterTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        mBtnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(Quizs.this,"Please select an answer",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion(){
         rb1.setTextColor(textColorDefualtRb);
         rb2.setTextColor(textColorDefualtRb);
         rb3.setTextColor(textColorDefualtRb);
         radioGroup.clearCheck();

         if (questionCounter < questionCounterTotal){
             currentQuestion = questionList.get(questionCounter);

             textViewQuestion.setText(currentQuestion.getQuestion());
             rb1.setText(currentQuestion.getOption1());
             rb2.setText(currentQuestion.getOption2());
             rb3.setText(currentQuestion.getOption3());

             questionCounter++;
             textViewQuestionCount.setText("Question: " + questionCounter + "/" +questionCounterTotal);
             answered = false;
             mBtnConfirmNext.setText("Confirm");
         } else {
             finishTest();
         }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rBtnSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rBtnSelected) +1;

        if (answerNr == currentQuestion.getAnswer()){
            score++;
            textViewScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswer()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCounterTotal) {
            mBtnConfirmNext.setText("Next");
        } else {
            mBtnConfirmNext.setText("Finish");
        }
    }

    private void finishTest() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK,resultIntent);
        finish();
    }

     @Override
     public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finishTest();
        } else {
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();
        }

         backPressedTime = System.currentTimeMillis();
     }
 }