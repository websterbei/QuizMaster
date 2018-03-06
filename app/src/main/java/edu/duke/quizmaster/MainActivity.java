package edu.duke.quizmaster;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clearStateButton = findViewById(R.id.clear_state);
        clearStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().deleteFile("state.json");
            }
        });

        Button startQuizButtonPersonality = findViewById(R.id.startQuizButtonPersonality);
        startQuizButtonPersonality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuiz(view.getContext());
            }

            private void openQuiz(Context mContext) {
                Intent intent = new Intent(mContext, QuizActivity.class);
                intent.putExtra("quizId", "pq1");
                mContext.startActivity(intent);
            }
        });

        Button startQuizButtonLinear = findViewById(R.id.startQuizButtonLinear);
        startQuizButtonLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuiz(view.getContext());
            }

            private void openQuiz(Context mContext) {
                Intent intent = new Intent(mContext, QuizActivity.class);
                intent.putExtra("quizId", "lq1");
                mContext.startActivity(intent);
            }
        });
    }
}
