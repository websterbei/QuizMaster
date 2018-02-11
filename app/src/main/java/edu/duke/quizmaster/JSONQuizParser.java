package edu.duke.quizmaster;

import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Webster on 2/11/18.
 */

public class JSONQuizParser {
    @Nullable
    public static Quiz parse(String quizString) {
        try {
            JSONObject quizJSON = new JSONObject(quizString);
            String title = quizJSON.getString("title");
            String type = quizJSON.getString("type");
            JSONArray questionsArray = quizJSON.getJSONArray("questions");
            Question[] questions = new Question[questionsArray.length()];
            for(int i=0; i<questionsArray.length(); i++) {
                JSONObject current = questionsArray.getJSONObject(i);
                String query = current.getString("query");
                JSONArray answersArray = current.getJSONArray("answers");
                String[] answers = new String[answersArray.length()];
                int[] scores = new int[answersArray.length()];
                for(int j=0; j<answersArray.length(); j++) {
                    JSONObject answer = answersArray.getJSONObject(j);
                    answers[j] = answer.getString("answer");
                    scores[j] = answer.getInt("score");
                }
                questions[i] = new Question(query, answers, scores);
            }
            switch(type) {
                case "linear quiz":
                    return new LinearQuiz(title, questions);
                case "personality quiz":
                    return new PersonalityQuiz(title, questions);
                default:
                    throw new JSONException("No matching quiz type");
            }
        } catch(JSONException e) {
            Log.d("Quiz parsing", "Failed to parse quiz string");
            e.printStackTrace();
        }
        return null;
    }
}
