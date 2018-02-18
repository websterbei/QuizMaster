package edu.duke.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        TextView result = findViewById(R.id.quiz_result_text_view);
        int totalScore = getIntent().getExtras().getInt("total_score");
        int playerScore = getIntent().getExtras().getInt("player_score");
        result.setText(Integer.toString(playerScore) + "/" + Integer.toString(totalScore));
    }
}
