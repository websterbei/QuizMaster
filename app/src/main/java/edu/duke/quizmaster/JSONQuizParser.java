package edu.duke.quizmaster;

import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

            switch(type) {
                case "linear quiz":
                    return new LinearQuiz(title, parseLinearQuestions(questionsArray));
                case "personality quiz":
                    JSONArray interpretation = quizJSON.getJSONArray("interpretation");
                    ArrayList<ArrayList<String>> interps = new ArrayList<>();
                    for(int i=0; i<interpretation.length(); i++) {
                        JSONArray interp = interpretation.optJSONArray(i);
                        ArrayList<String> entry = new ArrayList<>();
                        entry.add(interp.optString(0));
                        entry.add(interp.optString(1));
                        interps.add(entry);
                    }
                    return new PersonalityQuiz(title, parsePersonalityQuestions(questionsArray), interps);
                default:
                    throw new JSONException("No matching quiz type");
            }
        } catch(JSONException e) {
            Log.d("Quiz parsing", "Failed to parse quiz string");
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<LinearQuestion> parseLinearQuestions(JSONArray questionsArray) throws JSONException {
        ArrayList<LinearQuestion> questions = new ArrayList<>();
        for(int i=0; i<questionsArray.length(); i++) {
            JSONObject current = questionsArray.getJSONObject(i);
            String query = current.getString("query");
            JSONArray answersArray = current.getJSONArray("answers");
            ArrayList<String> answers = new ArrayList<>();
            ArrayList<Integer> scores = new ArrayList<>();
            for(int j=0; j<answersArray.length(); j++) {
                JSONObject answer = answersArray.getJSONObject(j);
                answers.add(answer.getString("answer"));
                scores.add(answer.getInt("score"));
            }
            questions.add(new LinearQuestion(query, answers, scores);
        }
        return questions;
    }

    private static ArrayList<PersonalityQuestion> parsePersonalityQuestions(JSONArray questionsArray) throws JSONException {
        ArrayList<PersonalityQuestion> questions = new ArrayList<>();
        for(int i=0; i<questionsArray.length(); i++) {
            JSONObject current = questionsArray.getJSONObject(i);
            String query = current.getString("query");
            JSONArray answersArray = current.getJSONArray("answers");
            ArrayList<String> answers = new ArrayList<>();
            ArrayList<ArrayList<Integer>> scores = new ArrayList<>();
            for(int j=0; j<answersArray.length(); j++) {
                JSONObject answer = answersArray.getJSONObject(j);
                answers.add(answer.getString("answer"));
                JSONArray score = answer.getJSONArray("score");
                ArrayList<Integer> curAnswerScore = new ArrayList<>();
                for(int k=0; k<score.length(); k++) {
                    curAnswerScore.add(score.optInt(k));
                }
                scores.add(curAnswerScore);
            }
            questions.add(new PersonalityQuestion(query, answers, scores);
        }
        return questions;
    }
}
