package edu.duke.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PersonalityQuizResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_quiz_result);
        TextView A_left = findViewById(R.id.text_view_A_left);
        TextView B_left = findViewById(R.id.text_view_B_left);
        TextView C_left = findViewById(R.id.text_view_C_left);
        TextView D_left = findViewById(R.id.text_view_D_left);
        TextView A_right = findViewById(R.id.text_view_A_right);
        TextView B_right = findViewById(R.id.text_view_B_right);
        TextView C_right = findViewById(R.id.text_view_C_right);
        TextView D_right = findViewById(R.id.text_view_D_right);
        TextView A_score = findViewById(R.id.text_view_A_score);
        TextView B_score = findViewById(R.id.text_view_B_score);
        TextView C_score = findViewById(R.id.text_view_C_score);
        TextView D_score = findViewById(R.id.text_view_D_score);
        ArrayList<ArrayList<String>> interpretations = (ArrayList<ArrayList<String>>) getIntent().getSerializableExtra("interpretations");
        A_left.setText(interpretations.get(0).get(0));
        A_right.setText(interpretations.get(0).get(1));
        B_left.setText(interpretations.get(1).get(0));
        B_right.setText(interpretations.get(1).get(1));
        C_left.setText(interpretations.get(2).get(0));
        C_right.setText(interpretations.get(2).get(1));
        D_left.setText(interpretations.get(3).get(0));
        D_right.setText(interpretations.get(3).get(1));
        ArrayList<Integer> playerScore = (ArrayList<Integer>) getIntent().getSerializableExtra("player_score");
        A_score.setText(Integer.toString(playerScore.get(0)));
        B_score.setText(Integer.toString(playerScore.get(1)));
        C_score.setText(Integer.toString(playerScore.get(2)));
        D_score.setText(Integer.toString(playerScore.get(3)));
    }
}
