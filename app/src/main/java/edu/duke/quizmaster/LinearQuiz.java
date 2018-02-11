package edu.duke.quizmaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Webster on 2/10/18.
 */

public class LinearQuiz implements Quiz {
    private String mTitle;
    private ArrayList<Question> mQuestions;

    public LinearQuiz(String title, Question[] questions) {
        this(title, questions, false);
    }

    public LinearQuiz(String title, Question[] questions, boolean shuffle) {
        this.mTitle = title;
        this.mQuestions = new ArrayList<>(Arrays.asList(questions));
        if(shuffle) {
            Collections.shuffle(mQuestions);
        }
    }

    @Override
    public String getTitle() {
        return this.mTitle;
    }

    @Override
    public int getSize() {
        return this.mQuestions.size();
    }

    @Override
    public Question getQuestion(int index) {
        if(index>=0 && index<this.mQuestions.size()) {
            return mQuestions.get(index);
        }
        throw new IndexOutOfBoundsException("Bad Index" + index);
    }
}
