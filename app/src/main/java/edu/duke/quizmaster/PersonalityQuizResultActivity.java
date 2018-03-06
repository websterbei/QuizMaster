package edu.duke.quizmaster;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
//        A_score.setText(String.format(String.valueOf(playerScore.get(0))));
//        B_score.setText(String.format(String.valueOf(playerScore.get(1))));
//        C_score.setText(String.format(String.valueOf(playerScore.get(2))));
//        D_score.setText(String.format(String.valueOf(playerScore.get(3))));
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        A_score.setText("Neutral");
        B_score.setText("Neutral");
        C_score.setText("Neutral");
        D_score.setText("Neutral");
        if(playerScore.get(0)<0) {
            A_left.setTypeface(boldTypeface);
            A_left.setTextColor(Color.BLUE);
        } else if(playerScore.get(0)>0) {
            A_right.setTypeface(boldTypeface);
            A_right.setTextColor(Color.BLUE);
        } else {
            A_score.setTypeface(boldTypeface);
            A_score.setTextColor(Color.BLUE);
        }
        if(playerScore.get(1)<0) {
            B_left.setTypeface(boldTypeface);
            B_left.setTextColor(Color.BLUE);
        } else if(playerScore.get(1)>0) {
            B_right.setTypeface(boldTypeface);
            B_right.setTextColor(Color.BLUE);
        } else {
            B_score.setTypeface(boldTypeface);
            B_score.setTextColor(Color.BLUE);
        }
        if(playerScore.get(2)<0) {
            C_left.setTypeface(boldTypeface);
            C_left.setTextColor(Color.BLUE);
        } else if(playerScore.get(2)>0) {
            C_right.setTypeface(boldTypeface);
            C_right.setTextColor(Color.BLUE);
        } else {
            C_score.setTypeface(boldTypeface);
            C_score.setTextColor(Color.BLUE);
        }
        if(playerScore.get(3)<0) {
            D_left.setTypeface(boldTypeface);
            D_left.setTextColor(Color.BLUE);
        } else if(playerScore.get(3)>0) {
            D_right.setTypeface(boldTypeface);
            D_right.setTextColor(Color.BLUE);
        } else {
            D_score.setTypeface(boldTypeface);
            D_score.setTextColor(Color.BLUE);
        }
    }

    @Override
    public void onBackPressed() {
        navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
    }
}
