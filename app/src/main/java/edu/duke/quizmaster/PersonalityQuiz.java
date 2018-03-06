package edu.duke.quizmaster;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
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
    private int mNumAnswered;

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
        this.mNumAnswered = index + 1;
    }

    @Override
    public JSONObject computeScore() {
        JSONObject scoreObject = new JSONObject();
        ArrayList<Integer> playerScore = new ArrayList<Integer>();
        playerScore.addAll(this.mQuestions.get(0).getScore(this.mAnswers.get(0)));
        for(int i=1; i<this.mQuestions.size(); i++) {
            ArrayList<Integer> questionScore = this.mQuestions.get(i).getScore(this.mAnswers.get(i));
            for(int j=0; j<questionScore.size(); j++) {
                playerScore.set(j, playerScore.get(j) + questionScore.get(j));
            }
        }
        try {
            scoreObject.put("player_score", playerScore);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scoreObject;
    }

    public ArrayList<ArrayList<String>> getInterpretations() {
        return this.mInterpretations;
    }

    @Override
    public int getTotalScore() {
        return this.mQuestions.size();
    }

    @Override
    public JSONObject getState() {
        JSONObject state = new JSONObject();
        try {
            state.put("user_answers", this.mAnswers);
            state.put("complete", this.mNumAnswered==this.mAnswers.size() ? true : false);
            return state;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }
}
