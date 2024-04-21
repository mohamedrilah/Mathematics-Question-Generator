package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class ElementaryFunctionQuestions implements Question {
    @Override
    public List<String> displayQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();
        String writtenQuestion;
        String generatedAnswer;
        String formattedAnswer;

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        writtenQuestion = "";
        generatedAnswer = "";
        formattedAnswer = "";

        int randomSlope = randomNumberGenerator.generateRandomNumber(10);
        int randomIntercept = randomNumberGenerator.generateRandomNumber(20);

        writtenQuestion = "Given the linear function y = " + randomSlope + "x + " + randomIntercept
                + " write down the slope and intercept of y";

        generatedAnswer += randomSlope;
        generatedAnswer += randomIntercept;
        formattedAnswer += "x = " + randomSlope + ", y = " + randomIntercept;

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);
        questionWithAnswer.add(formattedAnswer);

        return questionWithAnswer;
    }
}
