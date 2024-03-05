package com.mathematicsquestiongenerator.MathematicsQuestionGenerator;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.QuestionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionResponseTesting {

    @Test
    void testUserAnswer() {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setEnteredSolution("[1,2,3,4,5]");

        assertEquals("[1,2,3,4,5]", questionResponse.getEnteredSolution());
    }
}
