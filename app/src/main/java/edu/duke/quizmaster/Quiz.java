package edu.duke.quizmaster;

/**
 * Created by Webster on 2/6/18.
 */

interface Quiz {
    String getTitle();
    int getSize();
    Question getQuestion(int index);
    void setAnswer(int index, String answer);
    int getTotalScore();
}
