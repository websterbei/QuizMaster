package edu.duke.quizmaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Webster on 2/10/18.
 */

public class PersonalityQuiz implements Quiz {
    private String mTitle;
    private ArrayList<Question> mQuestions;
    private ArrayList<String> mAnswers;

    public PersonalityQuiz(String title, Question[] questions) {
        this(title, questions, false);
    }

    public PersonalityQuiz(String title, Question[] questions, boolean shuffle) {
        this.mTitle = title;
        this.mQuestions = new ArrayList<>(Arrays.asList(questions));
        if(shuffle) {
            Collections.shuffle(mQuestions);
        }
        this.mAnswers = new ArrayList<>();
        for(int i=0; i<this.mQuestions.size(); i++) {
            mAnswers.add("Default Answer");
        }
    }

    @Override
    public String getTitle() {
        return this.mTitle;
    }

    @Override
    public int getSize() {
        return this.mQuestions.size();
    }

    @Override
    public Question getQuestion(int index) {
        if(index>=0 && index<this.mQuestions.size()) {
            return mQuestions.get(index);
        }
        throw new IndexOutOfBoundsException("Bad Index" + index);
    }

    @Override
    public void setAnswer(int index, String answer) {
        this.mAnswers.set(index, answer);
    }

    @Override
    public int computeScore() {
        int playerScore = 0;
        for(int i=0; i<this.mQuestions.size(); i++) {
            playerScore += this.mQuestions.get(i).getScore(this.mAnswers.get(i));
        }
        return playerScore;
    }

    @Override
    public int getTotalScore() {
        return this.mQuestions.size();
    }
}
