package edu.duke.quizmaster;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Created by Webster on 3/6/18.
 */

public class PersonalityQuestionClassTest {
    @Test
    public void testPersonalityQuestion() {
        String query = "What is 1+1?";
        ArrayList<String> answers = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        ArrayList<Integer> optionA = new ArrayList<>(Arrays.asList(0, 0, 1, 1));
        ArrayList<Integer> optionB = new ArrayList<>(Arrays.asList(-1, 0, 1, -1));
        ArrayList<Integer> optionC = new ArrayList<>(Arrays.asList(0, -1, 1, 1));
        ArrayList<Integer> optionD = new ArrayList<>(Arrays.asList(0, -1, 0, 1));
        ArrayList<ArrayList<Integer>> scores = new ArrayList<>();
        scores.add(optionA);
        scores.add(optionB);
        scores.add(optionC);
        scores.add(optionD);
        PersonalityQuestion q = new PersonalityQuestion(query, answers, scores);
        assertEquals(q.getScore("1"), optionA);
        assertEquals(q.getScore("2"), optionB);
        assertEquals(q.getScore("3"), optionC);
        assertEquals(q.getScore("4"), optionD);

        assertEquals(q.getQuery(), "What is 1+1?");
    }
}
