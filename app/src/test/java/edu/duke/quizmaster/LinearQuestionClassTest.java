package edu.duke.quizmaster;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Created by Webster on 3/6/18.
 */

public class LinearQuestionClassTest {
    @Test
    public void testLinearQuestion() {
        String query = "What is 1+1?";
        ArrayList<String> answers = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(0,1,0,0));
        LinearQuestion q = new LinearQuestion(query, answers, scores);
        assertEquals(q.getScore("1"), 0);
        assertEquals(q.getScore("2"), 1);
        assertEquals(q.getScore("3"), 0);
        assertEquals(q.getScore("4"), 0);

        assertEquals(q.getQuery(), "What is 1+1?");
    }
}
