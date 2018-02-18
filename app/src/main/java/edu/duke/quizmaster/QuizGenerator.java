package edu.duke.quizmaster;

import android.content.Context;

/**
 * Created by Webster on 2/11/18.
 */

public class QuizGenerator {
    public static Quiz getRandomQuiz(final Context activityContext) {
        return JSONQuizFactory.getQuiz(activityContext, "lq1");
    }

    public static Quiz getQuizById(final Context activityContext, String quizId) {
        return JSONQuizFactory.getQuiz(activityContext, quizId);
    }
}
