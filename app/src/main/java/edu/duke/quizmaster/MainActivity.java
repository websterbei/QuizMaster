package edu.duke.quizmaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Button startQuizButtonPersonality = findViewById(R.id.startQuizButtonPersonality);
        System.out.println(StateManager.isComplete(getApplicationContext(), "lq1"));
     /*   startQuizButtonPersonality.setOnClickListener(new View.OnClickListener() {
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
        }); */
        String[] quizzes = this.getResources().getStringArray(R.array.quizzes);

        RecyclerView rv = findViewById(R.id.activity_main_recycler_view);
        rv.setAdapter(new QuizListAdapter(this, quizzes));
        rv.setLayoutManager(new LinearLayoutManager(this));


    }
}