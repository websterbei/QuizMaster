package edu.duke.quizmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class QuizActivity extends AppCompatActivity {
    private String mQuizId;
    private Quiz mQuiz;
    private int mQuizSize;
    private int mCurrentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_quiz);
        this.mQuizId = getIntent().getExtras().getString("quizId");
        this.mQuiz = QuizGenerator.getQuizById(getApplicationContext(), this.mQuizId);
        this.mQuizSize = this.mQuiz.getSize();
    }

    @Override
    protected void onStart() {
        System.out.println("Start");
        super.onStart();
        this.setRadioGroupEventListener("quiz_radio_group");
        this.mCurrentQuestionIndex = 0;
    }

    @Override
    protected void onResume() {
        System.out.println("Resume");
        super.onResume();
        this.displayQuestion(this.mCurrentQuestionIndex);
    }

    private void setRadioGroupEventListener(String radioGroupId) {
        RadioGroup radioGroup = getRadioGroupById(radioGroupId);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = radioGroup.findViewById(checkedId);
                if(checkedRadioButton == null) return;
                String answer = checkedRadioButton.getText().toString();
                mQuiz.setAnswer(mCurrentQuestionIndex, answer);
                radioGroup.clearCheck();
                nextQuestion();
            }
        });
    }

    private RadioGroup getRadioGroupById(String radioGroupId) {
        int resourceId = getResources().getIdentifier(radioGroupId, "id", getPackageName());
        return (RadioGroup) findViewById(resourceId);
    }

    private RadioButton getRadioButtonById(String buttonId) {
        int resourceId = getResources().getIdentifier(buttonId, "id", getPackageName());
        return (RadioButton) findViewById(resourceId);
    }

    private TextView getTextViewById(String textViewId) {
        int resourceId = getResources().getIdentifier(textViewId, "id", getPackageName());
        return (TextView) findViewById(resourceId);
    }

    private void nextQuestion() {
        if(this.mCurrentQuestionIndex+1 < this.mQuizSize) {
            this.mCurrentQuestionIndex++;
            displayQuestion(this.mCurrentQuestionIndex);
        } else {
            if(this.mQuiz instanceof LinearQuiz) {
                int totalScore = this.mQuiz.getTotalScore();
                JSONObject scoreObject = this.mQuiz.computeScore();
                int playerScore = 0;
                try {
                    playerScore = scoreObject.getInt("player_score");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(), QuizResultActivity.class);
                intent.putExtra("total_score", totalScore);
                intent.putExtra("player_score", playerScore);
                getApplicationContext().startActivity(intent);
            }
            else if(this.mQuiz instanceof PersonalityQuiz) {
                JSONObject scoreObject = this.mQuiz.computeScore();
                System.out.println("Printing Score");
                System.out.println(scoreObject);
            }
        }
    }

    private void displayQuestion(Question question) {
        //Setting text for quiz query
        String query = question.getQuery();
        TextView queryTextView = getTextViewById("quiz_query");
        queryTextView.setText(query);

        //Setting text for quiz options
        Iterator<String> options = question.getOptions().iterator();
        RadioButton A = getRadioButtonById("optionA");
        RadioButton B = getRadioButtonById("optionB");
        RadioButton C = getRadioButtonById("optionC");
        RadioButton D = getRadioButtonById("optionD");
        A.setText(options.next());
        B.setText(options.next());
        C.setText(options.next());
        D.setText(options.next());
    }

    private void displayQuestion(int index) {
        Question newQuestion = this.mQuiz.getQuestion(index);
        displayQuestion(newQuestion);
    }
}
