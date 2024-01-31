package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomQuestionGenerator {

    private Random random;

    public RandomQuestionGenerator() {
        random = new Random();
    }

    public int generateRandomNumber(int upperBound) {
        int randomNumber = random.nextInt(upperBound);

        return randomNumber;
    }

    public List<String> generateAdditionQuestion(int bound, int numQuestions) {
        String generatedQuestion = "";
        String generatedAnswer = "";
        int answer = 0;
        List<String> questionWithAnswer = new ArrayList<>();

        for (int i = 0; i < numQuestions; i++) {
            int randomNumber = random.nextInt(bound);

            generatedQuestion += randomNumber;

            answer += randomNumber;

            if (i < numQuestions - 1) {
                generatedQuestion += " + ";
            }
        }

        generatedAnswer += answer;

        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public String markAdditionQuestion(int enteredAnswer, int generatedAnswer) {
        String response = "";

        if (enteredAnswer == generatedAnswer) {
            response += "Well Done, the solution you submitted was the correct answer";
        } else {
            response += "Unfortunately, the solution you submitted was an incorrect answer, the correct answer is: "
                    + generatedAnswer;
        }

        return response;
    }
}
