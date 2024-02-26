package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class NumeralSystemsQuestions {
    private RandomNumberGenerator randomNumberGenerator
            = new RandomNumberGenerator();

    public List<String> generateBinaryToDenaryQuestion() {
        String generatedWrittenQuestion = "Convert the following binary number into denary: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int randomNumber = randomNumberGenerator.generateRandomNumber(2);
            generatedQuestion += randomNumber;
        }

        int answer = 0;
        int head = 128;

        for (int j = 0; j < generatedQuestion.length(); j++) {
            String z = String.valueOf(generatedQuestion.charAt(j));

            if (z.equals("1")) {
                answer += head;
                head = head / 2;
            } else {
                head = head / 2;
            }
        }

        generatedAnswer += answer;

        questionWithAnswer.add(generatedWrittenQuestion);
        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public List<String> generateDenaryToBinaryQuestion() {
        String generatedWrittenQuestion = "Convert the following denary number into binary: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        int randomNumber = randomNumberGenerator.generateRandomNumber(255);
        generatedQuestion += randomNumber;

        String answer = "";
        int head = 128;

        while (head >= 1) {
            if (randomNumber < head) {
                head = head / 2;
                answer += "0";
            } else if (randomNumber >= head) {
                answer += "1";
                randomNumber = randomNumber - head;
                head = head / 2;
            }
        }

        generatedAnswer += answer;

        questionWithAnswer.add(generatedWrittenQuestion);
        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

}
