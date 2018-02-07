package edu.duke.quizmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        TextView quizProblem = findViewById(R.id.quizProblem);
        String quizProblemName = getIntent().getExtras().getString("quizProblemName");
        int quizID = getResources().getIdentifier(quizProblemName, "array", getPackageName());
        final String[] quizProblemArray = getResources().getStringArray(quizID);
        quizProblem.setText(quizProblemArray[0]);
        RadioButton optionA = findViewById(R.id.optionA);
        RadioButton optionB = findViewById(R.id.optionB);
        RadioButton optionC = findViewById(R.id.optionC);
        RadioButton optionD = findViewById(R.id.optionD);
        optionA.setText(quizProblemArray[1]);
        optionB.setText(quizProblemArray[2]);
        optionC.setText(quizProblemArray[3]);
        optionD.setText(quizProblemArray[4]);

        Button submissionButton = findViewById(R.id.submit);
        submissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = (RadioButton) findViewById(selectedId);
                String selectedAnswer = selectedButton.getText().toString();
                int score = selectedAnswer.equals(quizProblemArray[5]) ? 1:0;
            }
        });
    }
}
