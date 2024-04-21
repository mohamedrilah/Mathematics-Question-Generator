package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class NumeralSystemsQuestions implements Question {
    @Override
    public List<String> displayQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();
        String writtenQuestion;
        String generatedQuestion;
        String generatedAnswer;
        String formattedAnswer;

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        int questionSelection = randomNumberGenerator.generateRandomNumber(2);

        if (questionSelection == 0) {
            generatedQuestion = "";
            generatedAnswer = "";
            formattedAnswer = "";

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
            formattedAnswer += generatedAnswer;

            questionWithAnswer.add(writtenQuestion);
            questionWithAnswer.add(generatedAnswer);
            questionWithAnswer.add(formattedAnswer);

        } else if (questionSelection == 1) {
            generatedQuestion = "";
            generatedAnswer = "";
            formattedAnswer = "";

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

            writtenQuestion = "Convert the following Denary value " + generatedQuestion + " into Binary";
            generatedAnswer += answer;
            formattedAnswer += generatedAnswer;

            questionWithAnswer.add(writtenQuestion);
            questionWithAnswer.add(generatedAnswer);
            questionWithAnswer.add(formattedAnswer);
        }

        return questionWithAnswer;
    }
}
