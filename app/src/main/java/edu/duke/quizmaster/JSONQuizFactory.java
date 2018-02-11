package edu.duke.quizmaster;

import android.content.Context;

/**
 * Created by Webster on 2/11/18.
 */

public class JSONQuizFactory {
    public static String getQuizString(final Context activityContext, String resourceName) {
        int id = activityContext.getResources().getIdentifier(resourceName, "string", activityContext.getPackageName());
        String quizString = activityContext.getResources().getString(id);
        return quizString;
    }

    public static Quiz getQuiz(final Context activityContext, String resourceName) {
        String quizString = getQuizString(activityContext, resourceName);
        Quiz quiz = JSONQuizParser.parse(quizString);
    }
}
