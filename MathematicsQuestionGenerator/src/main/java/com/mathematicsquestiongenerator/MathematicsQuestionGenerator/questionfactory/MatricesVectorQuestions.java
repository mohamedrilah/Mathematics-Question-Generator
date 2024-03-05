package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatricesVectorQuestions implements Question {
    @Override
    public List<String> displayQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();
        String writtenQuestion;
        String generatedQuestion;
        String generatedAnswer;

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        generatedQuestion = "";
        generatedAnswer = "";

        int scalarMultiplier = randomNumberGenerator.generateRandomNumber(10);
        int[] matrix = new int[4];

        for (int i = 0; i < matrix.length; i++) {
            int randomNumber = randomNumberGenerator.generateRandomNumber(10);
            matrix[i] = randomNumber;
        }

        generatedQuestion += Arrays.toString(matrix);

        writtenQuestion = "Let the Matrix M = " + generatedQuestion + ". Calculate the Matrix " + scalarMultiplier + "M";

        for (int i = 0; i < matrix.length; i++) {
            int num = matrix[i];
            int calc = num * scalarMultiplier;
            generatedAnswer += calc;
        }

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }
}