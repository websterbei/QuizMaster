package edu.duke.quizmaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jose on 2/22/18.
 */

public class PersonalityQuestion implements Question {
    private String mQuery;
    private Map<String, ArrayList<Integer>> mScoreMap;

    public PersonalityQuestion(String q, ArrayList<String> answers, ArrayList<ArrayList<Integer>> scores) {
        this.mQuery = q;
        this.mScoreMap = new HashMap<>();
        for(int i=0; i<answers.size(); i++) {
            mScoreMap.put(answers.get(i), scores.get(i));
        }
    }

    public Map<String, ArrayList<Integer>> getScoreMap() {
        return this.mScoreMap;
    }

    public ArrayList<Integer> getScore(String myAnswer) {
        return mScoreMap.get(myAnswer);
    }

    public String getQuery() {
        return this.mQuery;
    }

    public Iterable<String> getOptions() {
        return this.mScoreMap.keySet();
    }
}
