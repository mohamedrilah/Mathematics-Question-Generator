package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatricesAndVectorsQuestions {
    private RandomNumberGenerator randomNumberGenerator
            = new RandomNumberGenerator();

    public List<String> generateMatrixMultiplicationQuestion() {
        int scalarMultiplier = randomNumberGenerator.generateRandomNumber(10);

        String generatedWrittenQuestion = "What is the new matrix with " + scalarMultiplier + " : ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        int[] matrix = new int[4];

        for (int i = 0; i < matrix.length; i++) {
            int randomNumber = randomNumberGenerator.generateRandomNumber(10);
            matrix[i] = randomNumber;
        }

        generatedQuestion += Arrays.toString(matrix);

        for (int i = 0; i < matrix.length; i++) {
            int num = matrix[i];
            int calc = num * scalarMultiplier;
            generatedAnswer += calc;
        }

        System.out.println(generatedAnswer);

        questionWithAnswer.add(generatedWrittenQuestion);
        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }
}
