package edu.duke.quizmaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Webster on 2/10/18.
 */

public class PersonalityQuiz implements Quiz {
    private String mTitle;
    private ArrayList<PersonalityQuestion> mQuestions;
    private ArrayList<String> mAnswers;
    private ArrayList<ArrayList<String>> mInterpretations;

    public PersonalityQuiz(String title, ArrayList<PersonalityQuestion> questions, ArrayList<ArrayList<String>> interpretations) {
        this(title, questions, interpretations, false);
    }

    public PersonalityQuiz(String title, ArrayList<PersonalityQuestion> questions, ArrayList<ArrayList<String>> interpretations, boolean shuffle) {
        this.mTitle = title;
        this.mQuestions = questions;
        this.mInterpretations = interpretations;
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

    public ArrayList<Integer> computeScore() {
        ArrayList<Integer> playerScore = new ArrayList<Integer>();
        playerScore.addAll(this.mQuestions.get(0).getScore(this.mAnswers.get(0)));
        for(int i=1; i<this.mQuestions.size(); i++) {
            ArrayList<Integer> ans = this.mQuestions.get(0).getScore(this.mAnswers.get(0));
            for(int j=0; j<ans.size(); j++) {
                playerScore.add(j, playerScore.get(j) + ans.get(j));
            }
        }
        return playerScore;
    }

    @Override
    public int getTotalScore() {
        return this.mQuestions.size();
    }
}
