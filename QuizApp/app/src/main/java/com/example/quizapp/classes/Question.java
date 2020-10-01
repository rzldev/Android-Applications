package com.example.quizapp.classes;

import java.util.Random;

public abstract class Question {
    private int[] numbers = new int[3];
    private String[] operators = new String[]{"+", "-", "x", ":"};
    private int[] choosedOperators;
    private String question;

    public int[] getNumbers() {
        return numbers;
    }

    public String[] getOperators() {
        return operators;
    }

    public int[] getChoosedOperators() {
        return choosedOperators;
    }

    public void generateQuestion() {
        int min = 1;
        int max = 30;

        for (int x = 0; x < 3; x++) {
            numbers[x] = (int) (Math.random() * (max - min + 1) + min);
        }

        Random rand = new Random();
        int firstOperator = rand.nextInt(operators.length);
        int secondOperator = rand.nextInt(operators.length);

        choosedOperators = new int[]{0, firstOperator, secondOperator};

        question = numbers[0] + " " + operators[firstOperator] + " " + numbers[1] + " "
                + operators[secondOperator] + " " + numbers[2] + " = ?";
    }

    public String getQuestion() {
        return question;
    }

    protected double checkOperator(int operator, int number) {
        return 0;
    }
}
