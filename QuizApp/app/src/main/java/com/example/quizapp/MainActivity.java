package com.example.quizapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.quizapp.classes.Answer;
import com.example.quizapp.classes.Score;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> answers;
    private TextView tvQuestion, tvTimer, tvQuestionRight, correct;
    private Button btnNext, btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private ConstraintLayout answerLayout;

    private int totalQuestion;
    private double answer;

    private Answer a;
    private Score s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestionRight = findViewById(R.id.tvQuestionRight);
        correct = findViewById(R.id.correct);
        btnNext = findViewById(R.id.btnNext);
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);
        answerLayout = findViewById(R.id.answerLayout);

        btnNext.setOnClickListener(this);
        btnAnswer1.setOnClickListener(this);
        btnAnswer2.setOnClickListener(this);
        btnAnswer3.setOnClickListener(this);
        btnAnswer4.setOnClickListener(this);

        startDisplay();
    }

    private void startDisplay() {
        tvQuestion.setText("Simple Mathematic Quiz App");
        btnNext.setText("START QUIZ");
        btnNext.setBackgroundColor(getResources().getColor(R.color.limeGreen));

        tvQuestion.setTranslationY(500);
        btnNext.setTranslationY(-1000);
        tvTimer.setTranslationY(-500);
        tvQuestionRight.setTranslationY(-500);
        answerLayout.setAlpha(0);
    }

    private  void endDisplay() {
        tvQuestion.setText("Your score is " + s.getScore());
        btnNext.setText("START QUIZ");
        btnNext.setBackgroundColor(getResources().getColor(R.color.limeGreen));

        tvQuestion.animate().translationYBy(500).setDuration(800);
        btnNext.animate().translationYBy(-1000).setDuration(800);
        tvTimer.animate().translationYBy(-500).setDuration(800);
        tvQuestionRight.animate().translationYBy(-500).setDuration(800);
        answerLayout.animate().alpha(0f).setDuration(800);
        correct.setVisibility(View.GONE);
    }

    CountDownTimer questionTimer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvTimer.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            if (millisUntilFinished >= 21000) {
                tvTimer.setBackgroundColor(getResources().getColor(R.color.limeGreen));
            } else if (millisUntilFinished >= 11000) {
                tvTimer.setBackgroundColor(getResources().getColor(R.color.amber));
            } else {
                tvTimer.setBackgroundColor(getResources().getColor(R.color.red));
            }
        }

        @Override
        public void onFinish() {
            correct.setText("Time's up");
            correct.setTextColor(getResources().getColor(R.color.red));
            correct.setVisibility(View.VISIBLE);
        }
    };

    private void showAnswers() {
        btnAnswer1.setText(new DecimalFormat("##.##").format(Double.parseDouble(answers.get(0))));
        btnAnswer2.setText(new DecimalFormat("##.##").format(Double.parseDouble(answers.get(1))));
        btnAnswer3.setText(new DecimalFormat("##.##").format(Double.parseDouble(answers.get(2))));
        btnAnswer4.setText(new DecimalFormat("##.##").format(Double.parseDouble(answers.get(3))));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (btnNext.getText() == "START QUIZ") {
                    startQuiz();
                } else if (btnNext.getText() == "CHECK") {
                    checkAnswer();
                    nextButton();
                    questionTimer.cancel();
                } else if (btnNext.getText() == "NEXT") {
                    if (s.getCurrentQuestion() >= 5) {
                        endDisplay();

                    } else {
                        correct.setVisibility(View.GONE);

                        chooseAnswer(5);
                        nextQuestion();
                        checkButton();
                    }
                }
                return;
            case R.id.btnAnswer1:
                chooseAnswer(0);
                return;
            case R.id.btnAnswer2:
                chooseAnswer(1);
                return;
            case R.id.btnAnswer3:
                chooseAnswer(2);
                return;
            case R.id.btnAnswer4:
                chooseAnswer(3);
                return;
        }
    }

    private void checkAnswer() {
        if (a.isCorrect()) {
            correct.setText("Correct!!!");
            correct.setTextColor(getResources().getColor(R.color.limeGreen));
            correct.setVisibility(View.VISIBLE);

            s.setCurrentQuestion();
            s.setQuestionRight();
        } else {
            correct.setText("Wrong!!!");
            correct.setTextColor(getResources().getColor(R.color.red));
            correct.setVisibility(View.VISIBLE);

            s.setCurrentQuestion();
        }
    }

    private void startQuiz() {
        tvQuestion.setText("Simple Mathematic Quiz App");
        checkButton();

        tvQuestion.animate().translationYBy(-500).setDuration(800);
        btnNext.animate().translationYBy(1000).setDuration(800);
        tvTimer.animate().translationYBy(500).setDuration(800);
        tvQuestionRight.animate().translationYBy(500).setDuration(800);
        answerLayout.animate().alpha(1f).setDuration(800);

        totalQuestion = 5;
        s = new Score(totalQuestion);

        nextQuestion();
    }

    private void nextQuestion() {
        tvQuestionRight.setText(String.valueOf(s.getCurrentQuestion()) +
                "/" + String.valueOf(s.getTotalQuestion()));

        a = new Answer();

        a.generateQuestion();
        tvQuestion.setText(a.getQuestion());

        answer = a.getAnswer();
        answers = a.getAnswers();

        showAnswers();
        questionTimer.start();
    }

    private void checkButton() {
        btnNext.setText("CHECK");
        btnNext.setBackgroundColor(getResources().getColor(R.color.limeGreen));
    }

    private void nextButton() {
        btnNext.setText("NEXT");
        btnNext.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    private void chooseAnswer(int answerPosition) {
        if (answerPosition == 0) {
            coloringBox(btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4);
            a.setChoosedAnswer(Double.valueOf(String.valueOf(btnAnswer1.getText())));
        } else if (answerPosition == 1) {
            coloringBox(btnAnswer2, btnAnswer1, btnAnswer3, btnAnswer4);
            a.setChoosedAnswer(Double.valueOf(String.valueOf(btnAnswer2.getText())));
        } else if (answerPosition == 2) {
            coloringBox(btnAnswer3, btnAnswer1, btnAnswer2, btnAnswer4);
            a.setChoosedAnswer(Double.valueOf(String.valueOf(btnAnswer3.getText())));
        } else if (answerPosition == 3) {
            coloringBox(btnAnswer4, btnAnswer1, btnAnswer2, btnAnswer3);
            a.setChoosedAnswer(Double.valueOf(String.valueOf(btnAnswer4.getText())));
        } else {
            btnAnswer1.setBackgroundResource(R.drawable.btn_answer);
            btnAnswer1.setTextColor(this.getResources().getColor(R.color.darkGrey
            ));
            btnAnswer2.setBackgroundResource(R.drawable.btn_answer);
            btnAnswer2.setTextColor(this.getResources().getColor(R.color.darkGrey));
            btnAnswer3.setBackgroundResource(R.drawable.btn_answer);
            btnAnswer3.setTextColor(this.getResources().getColor(R.color.darkGrey));
            btnAnswer4.setBackgroundResource(R.drawable.btn_answer);
            btnAnswer4.setTextColor(this.getResources().getColor(R.color.darkGrey));
        }
    }

    private void coloringBox(Button choosedBox, Button unchoosedBox1, Button unchoosedBox2, Button unchoosedBox3) {
        choosedBox.setBackgroundResource(R.drawable.btn_answer_choose);
        choosedBox.setTextColor(this.getResources().getColor(R.color.limeGreen));
        unchoosedBox1.setBackgroundResource(R.drawable.btn_answer);
        unchoosedBox1.setTextColor(this.getResources().getColor(R.color.darkGrey));
        unchoosedBox2.setBackgroundResource(R.drawable.btn_answer);
        unchoosedBox2.setTextColor(this.getResources().getColor(R.color.darkGrey));
        unchoosedBox3.setBackgroundResource(R.drawable.btn_answer);
        unchoosedBox3.setTextColor(this.getResources().getColor(R.color.darkGrey));
    }
}