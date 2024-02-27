package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class ElementaryFunctionQuestions {

    private RandomNumberGenerator randomNumberGenerator
            = new RandomNumberGenerator();

    public List<String> generateLinearFunctionQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();

        String writtenQuestion;
        String generatedAnswer = "";

        int randomSlope = randomNumberGenerator.generateRandomNumber(10);
        int randomIntercept = randomNumberGenerator.generateRandomNumber(20);

        writtenQuestion = "Given the linear function y = " + randomSlope + "x + " + randomIntercept
                + " write down the slope and intercept of y";

        generatedAnswer += randomSlope;
        generatedAnswer += randomIntercept;

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

}
