package edu.duke.quizmaster;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Webster on 2/10/18.
 */

public interface Question {


    public String getQuery();

    public Iterable<String> getOptions();
}