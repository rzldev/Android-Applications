package com.example.quizapp.classes;

public class Score {
    private int totalQuestion;
    private int currentQuestion = 0;
    private int score = 0;
    private int questionRight = 0;

    public Score(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getQuestionRight() {
        return questionRight;
    }

    public void setQuestionRight() {
        this.questionRight++;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion() {
        this.currentQuestion++;
    }

    public int getScore() {
        setScore();
        return score;
    }

    public void setScore() {
        this.score = 100 * getQuestionRight() / getTotalQuestion();
    }
}
