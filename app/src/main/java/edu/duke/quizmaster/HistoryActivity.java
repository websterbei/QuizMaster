package edu.duke.quizmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        String[] quizzes = this.getResources().getStringArray(R.array.quizzes);
        StringBuilder resultHistory = new StringBuilder();
        for(int i=0; i<quizzes.length; i++) {
            String quizString = JSONQuizFactory.getQuizString(getApplicationContext(), quizzes[i]);
            JSONObject quizObject = null;
            try {
                quizObject = new JSONObject(quizString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String quizName = null;
            try {
                quizName = quizObject.getString("title");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            resultHistory.append(quizName);
            resultHistory.append("\n\t\t\t");
            ArrayList<String> results = HistoryManager.getResultHistory(getApplicationContext(), quizzes[i]);
            if(results==null) continue;
            for(String result : results) {
                resultHistory.append(result);
                resultHistory.append("\n\t\t\t");
            }
            resultHistory.deleteCharAt(resultHistory.length()-1);
            resultHistory.deleteCharAt(resultHistory.length()-1);
            resultHistory.deleteCharAt(resultHistory.length()-1);
        }

        TextView historyTextView = findViewById(R.id.history_textview);
        historyTextView.setText(resultHistory.toString());

        Button clearHistory = findViewById(R.id.clear_history);
        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().deleteFile("history.json");
                TextView historyTextView = findViewById(R.id.history_textview);
                historyTextView.setText("");
            }
        });
    }

}