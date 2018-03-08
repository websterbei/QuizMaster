package edu.duke.quizmaster;
import org.json.JSONException;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by Webster on 3/8/18.
 */

public class ParserTest {

    @Test
    public void testJSONParser() throws JSONException {
        String jsonQuiz = "{\"title\": \"First Linear Quiz\", \"type\": \"linear quiz\", \"questions\": [{\"query\": \"What is the answer to 1+1?\", \"answers\": [{\"answer\": \"2\", \"score\": 1}, {\"answer\": \"3\", \"score\": 0}, {\"answer\": \"4\", \"score\": 0}, {\"answer\": \"5\", \"score\": 0}]}, {\"query\": \"What is the answer to 2+2?\", \"answers\": [{\"answer\": \"2\", \"score\": 0}, {\"answer\": \"3\", \"score\": 0}, {\"answer\": \"4\", \"score\": 1}, {\"answer\": \"5\", \"score\": 0}]}, {\"query\": \"How spicy is spicy?\", \"answers\": [{\"answer\": \"very spicy\", \"score\": 0}, {\"answer\": \"somewhat spicy\", \"score\": 1}, {\"answer\": \"little spicy\", \"score\": 0}, {\"answer\": \"not spicy at all\", \"score\": 0}]}]}";
        //String jsonQuiz = "{\\\"title\\\": \\\"First Linear Quiz\\\", \\\"type\\\": \\\"linear quiz\\\", \\\"questions\\\": [{\\\"query\\\": \\\"What is the answer to 1+1?\\\", \\\"answers\\\": [{\\\"answer\\\": 2, \\\"score\\\": 1}, {\\\"answer\\\": 3, \\\"score\\\": 0}, {\\\"answer\\\": 4, \\\"score\\\": 0}, {\\\"answer\\\": 5, \\\"score\\\": 0}]}, {\\\"query\\\": \\\"What is the answer to 2+2?\\\", \\\"answers\\\": [{\\\"answer\\\": 2, \\\"score\\\": 0}, {\\\"answer\\\": 3, \\\"score\\\": 0}, {\\\"answer\\\": 4, \\\"score\\\": 1}, {\\\"answer\\\": 5, \\\"score\\\": 0}]}, {\\\"query\\\": \\\"How spicy is spicy?\\\", \\\"answers\\\": [{\\\"answer\\\": \\\"very spicy\\\", \\\"score\\\": 0}, {\\\"answer\\\": \\\"somewhat spicy\\\", \\\"score\\\": 1}, {\\\"answer\\\": \\\"little spicy\\\", \\\"score\\\": 0}, {\\\"answer\\\": \\\"not spicy at all\\\", \\\"score\\\": 0}]}]}";
        Quiz q = JSONQuizParser.parse(jsonQuiz);

        assertEquals(q.getTitle(), "First Linear Quiz");
        assertEquals(q.getSize(), 3);
        q.setAnswer(0, "2");
        q.setAnswer(1, "4");
        q.setAnswer(2, "very spicy");
        assertEquals(q.computeScore().getInt("player_score"), 2);
    }
}
