package edu.duke.quizmaster;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by Jose on 3/6/18.
 */

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        TextView mQuizTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mLinearLayout = itemView.findViewById(R.id.quiz_holder_linear_layout);
            Log.d("linearlayout", "set");
            this.mQuizTitle = itemView.findViewById(R.id.quiz_name_text_view);
        }
    }

    Context mContext;
    String[] mQuizList;

    public QuizListAdapter(final Context context, String[] quizzes) {
        this.mContext = context;
        this.mQuizList = quizzes;
    }

    @Override
    public int getItemCount() {
        return mQuizList.length;
    }

    private void openQuiz(String quizId) {
        Intent intent = new Intent(mContext, QuizActivity.class);
        intent.putExtra("quiz_id", quizId);
        mContext.startActivity(intent);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Inflated", "true");
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = mInflater.inflate(R.layout.quiz_holder, parent, false);
        final ViewHolder quizHolder = new ViewHolder(row);

        quizHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuiz(mQuizList[quizHolder.getAdapterPosition()]);
            }
        });

        return quizHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String quizId = mQuizList[position];
        String quizString = JSONQuizFactory.getQuizString(mContext, quizId);
        //String quizString = JSONQuizFactory.getQuizString(mContext, quizId);
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
        holder.mQuizTitle.setText(quizName);
    }
}