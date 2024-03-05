package com.mathematicsquestiongenerator.MathematicsQuestionGenerator;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.QuestionMarker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionMarkingTesting {

    @Test
    void testCorrectResponse() {
        QuestionMarker questionMarker = new QuestionMarker();

        int userInput = 5;
        int correctAnswer = 5;

        String generatedResponse = questionMarker.markQuestion(userInput, correctAnswer);
        String testResponse = "Well Done, the solution you submitted was the correct answer";

        assertEquals(testResponse, generatedResponse);
    }

    @Test
    void testIncorrectResponse() {
        QuestionMarker questionMarker = new QuestionMarker();

        int userInput = 5;
        int correctAnswer = 10;

        String generatedResponse = questionMarker.markQuestion(userInput, correctAnswer);
        String testResponse = "Unfortunately, the solution you submitted was an incorrect answer, " +
                "the correct answer is: " + correctAnswer;

        assertEquals(testResponse, generatedResponse);
    }
}
