package edu.duke.quizmaster;

import org.json.JSONObject;

/**
 * Created by Webster on 2/6/18.
 */

interface Quiz {
    String getTitle();
    int getSize();
    Question getQuestion(int index);
    void setAnswer(int index, String answer);
    int getTotalScore();
    JSONObject computeScore();
    JSONObject getState();
    int resume(JSONObject state);
}
