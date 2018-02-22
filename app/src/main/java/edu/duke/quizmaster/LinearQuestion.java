package edu.duke.quizmaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jose on 2/22/18.
 */

public class LinearQuestion implements Question {
        private String mQuery;
        private Map<String, Integer> mScoreMap;

        public LinearQuestion(String q, ArrayList<String> answers, ArrayList<Integer> scores) {
            this.mQuery = q;
            this.mScoreMap = new HashMap<>();
            for(int i=0; i<answers.size(); i++) {
                mScoreMap.put(answers.get(i), scores.get(i));
            }
        }

        public LinearQuestion(String q, String correctAnswer, ArrayList<String> wrongAnswers) {
            this.mQuery = q;
            this.mScoreMap = new HashMap<>();
            for(int i=0; i<wrongAnswers.size(); i++) {
                mScoreMap.put(wrongAnswers.get(i), 0);
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
