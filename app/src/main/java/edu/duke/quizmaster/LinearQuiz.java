package edu.duke.quizmaster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Webster on 2/10/18.
 */

public class LinearQuiz implements Quiz {
    private String mTitle;
    private ArrayList<LinearQuestion> mQuestions;
    private ArrayList<String> mAnswers;
    private int mNumAnswered;

    public LinearQuiz(String title, ArrayList<LinearQuestion> questions) {
        this(title, questions, false);
    }

    public LinearQuiz(String title, ArrayList<LinearQuestion> questions, boolean shuffle) {
        this.mTitle = title;
        this.mQuestions = questions;
        if(shuffle) {
            Collections.shuffle(this.mQuestions);
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

    public JSONObject computeScore() {
        int playerScore = 0;
        for(int i=0; i<this.mQuestions.size(); i++) {
            playerScore += this.mQuestions.get(i).getScore(this.mAnswers.get(i));
        }
        JSONObject scoreObject = new JSONObject();
        try {
            scoreObject.put("player_score", playerScore);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scoreObject;
    }

    @Override
    public int getTotalScore() {
        return this.mQuestions.size();
    }

    @Override
    public JSONObject getState() {
        JSONObject state = new JSONObject();
        try {
            JSONArray answers = new JSONArray(this.mAnswers);
            System.out.println(answers);
            state.put("user_answers", answers);
            state.put("complete", this.mNumAnswered==this.mAnswers.size() ? true : false);
            state.put("num_answered", this.mNumAnswered);
            return state;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public int resume(JSONObject state) {
        try {
            this.mNumAnswered = state.getInt("num_answered");
            System.out.println(state.get("user_answers"));
            JSONArray answers = state.getJSONArray("user_answers");
            for(int i=0; i<answers.length(); i++) {
                this.mAnswers.set(i, answers.getString(i));
            }
            //for(String answer : this.mAnswers) System.out.println(answer);
            return this.mNumAnswered;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
