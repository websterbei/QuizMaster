package edu.duke.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private boolean[] mIsComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clearStateButton = findViewById(R.id.clear_state);
        clearStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().deleteFile("state.json");
                onStart();
            }
        });

        String[] quizzes = this.getResources().getStringArray(R.array.quizzes);
        mIsComplete = new boolean[quizzes.length];
        for(int i=0; i<quizzes.length; i++) {
            mIsComplete[i] = StateManager.isComplete(getApplicationContext(), quizzes[i]);
        }

        this.mRv = findViewById(R.id.activity_main_recycler_view);
        mRv.setAdapter(new QuizListAdapter(this, quizzes, mIsComplete));
        mRv.setLayoutManager(new LinearLayoutManager(this));

        final Button getHistoryButton = findViewById(R.id.get_history);
        getHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        String[] quizzes = this.getResources().getStringArray(R.array.quizzes);
        mIsComplete = new boolean[quizzes.length];
        for(int i=0; i<quizzes.length; i++) {
            mIsComplete[i] = StateManager.isComplete(getApplicationContext(), quizzes[i]);
            Log.d(quizzes[i], Boolean.toString(mIsComplete[i]));
        }

        this.mRv = findViewById(R.id.activity_main_recycler_view);
        mRv.setAdapter(new QuizListAdapter(this, quizzes, mIsComplete));
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }
}