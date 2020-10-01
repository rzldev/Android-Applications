package com.example.quizapp.classes;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Answer extends Question {
    private double answer;

    private double choosedAnswer;
    private ArrayList answers;

    @Override
    public void generateQuestion() {
        super.generateQuestion();

        setAnswer();
    }

    public void setAnswer() {
        if (getChoosedOperators()[1] < 2 && getChoosedOperators()[2] >= 2) {
            int y = getChoosedOperators()[1];
            getChoosedOperators()[1] = getChoosedOperators()[2];
            getChoosedOperators()[2] = y;

            y = getNumbers()[0];
            getNumbers()[0] = getNumbers()[1];
            getNumbers()[1] = getNumbers()[2];
            getNumbers()[2] = y;
        }

        for (int x = 0; x < getChoosedOperators().length; x++) {
            answer = checkOperator(getChoosedOperators()[x], getNumbers()[x]);
        }
    }

    public double getAnswer() {
        return answer;
    }

    @Override
    protected double checkOperator(int operator, int number) {
        if (operator == 0) {
            return answer + number;
        } else if (operator == 1) {
            return answer - number;
        } else if (operator == 2) {
            return answer * number;
        } else if (operator == 3) {
            return answer / number;
        }

        return answer;
    }

    public ArrayList getAnswers() {
        answers = new ArrayList<>();
        answers.add(String.valueOf(answer));

        double min = answer - (answer * 0.2);
        double max = answer + (answer * 0.2);

        for (int x = 1; x <= 3; x++) {
            answers.add(String.valueOf(Math.random() * (max - min + 1) + min));
        }

        return answers;
    }

    public double getChoosedAnswer() {
        return choosedAnswer;
    }

    public void setChoosedAnswer(double choosedAnswer) {
        this.choosedAnswer = choosedAnswer;
    }

    public boolean isCorrect() {
        if (getChoosedAnswer() ==
                Double.parseDouble(new DecimalFormat("##.##").format(answer))) {
            return true;
        } else {
            return false;
        }
    }
}
