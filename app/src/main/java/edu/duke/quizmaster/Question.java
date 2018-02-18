package edu.duke.quizmaster;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Webster on 2/10/18.
 */

public class Question {
    private String mQuery;
    private Map<String, Integer> mScoreMap;

    public Question(String q, String[] answers, int[] scores) {
        this.mQuery = q;
        this.mScoreMap = new HashMap<>();
        for(int i=0; i<answers.length; i++) {
            mScoreMap.put(answers[i], scores[i]);
        }
    }

    public Question(String q, String correctAnswer, String[] wrongAnswers) {
        this.mQuery = q;
        this.mScoreMap = new HashMap<>();
        for(int i=0; i<wrongAnswers.length; i++) {
            mScoreMap.put(wrongAnswers[i], 0);
        }
        mScoreMap.put(correctAnswer, 1);
    }

    public Map<String, Integer> getScoreMap() {
        return this.mScoreMap;
    }

    public int getScore(String myAnswer) {
        return mScoreMap.get(myAnswer);
    }

    public String getQuery() {
        return this.mQuery;
    }

    public Iterable<String> getOptions() {
        return this.mScoreMap.keySet();
    }
}
