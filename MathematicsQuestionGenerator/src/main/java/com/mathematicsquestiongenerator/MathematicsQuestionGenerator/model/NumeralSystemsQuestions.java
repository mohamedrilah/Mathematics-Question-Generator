package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class NumeralSystemsQuestions {
    private RandomNumberGenerator randomNumberGenerator
            = new RandomNumberGenerator();

    public List<String> generateBinaryToDenaryQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();

        String writtenQuestion;
        String generatedQuestion = "";
        String generatedAnswer = "";

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

        writtenQuestion = "Convert the following Binary number " + generatedQuestion + " into denary";
        generatedAnswer += answer;

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public List<String> generateDenaryToBinaryQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();

        String writtenQuestion;
        String generatedQuestion = "";
        String generatedAnswer = "";

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

        writtenQuestion = "Conver the following Denary value " + generatedQuestion + " into Binary";
        generatedAnswer += answer;

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

}
