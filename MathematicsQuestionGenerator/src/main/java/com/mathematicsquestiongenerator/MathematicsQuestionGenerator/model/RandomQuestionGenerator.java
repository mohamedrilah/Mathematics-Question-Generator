package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.*;

public class RandomQuestionGenerator {

    private Random random;

    public RandomQuestionGenerator() {
        random = new Random();
    }

    public int generateRandomNumber(int upperBound) {
        int randomNumber = random.nextInt(upperBound);

        return randomNumber;
    }

    public List<String> generateBinaryToDenaryQuestion() {
        String generatedWrittenQuestion = "Q. Convert the following binary number into denary: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(2);
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
        String generatedWrittenQuestion = "Q. Convert the following denary number into binary: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        int randomNumber = random.nextInt(255);
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

    public List<String> generateSetUnionQuestion() {
        String generatedWrittenQuestion = "Q. What is the union of the following sets: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        Set<Integer> setOne = new HashSet<>();
        Set<Integer> setTwo = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int randomnumber = random.nextInt(10);
            setOne.add(randomnumber);
        }

        for (int i = 0; i < 4; i++) {
            int randomnumber = random.nextInt(10);
            setTwo.add(randomnumber);
        }

        generatedQuestion += setOne + " ∪ " + setTwo;

        Set<Integer> unionSet = new HashSet<>(setOne);
        unionSet.addAll(setTwo);

        generatedAnswer += unionSet;
        generatedAnswer = generatedAnswer.replaceAll("[^\\d]", "");

        questionWithAnswer.add(generatedWrittenQuestion);
        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public List<String> generateSetIntersectQuestion() {
        String generatedWrittenQuestion = "Q. What is the intersect of the following sets: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        Set<Integer> setOne = new HashSet<>();
        Set<Integer> setTwo = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int randomnumber = random.nextInt(50);
            setOne.add(randomnumber);
        }

        for (int i = 0; i < 4; i++) {
            int randomnumber = random.nextInt(50);
            setTwo.add(randomnumber);
        }

        generatedQuestion += setOne + " ∩ " + setTwo;

        Set<Integer> intersectSet = new HashSet<>(setOne);
        intersectSet.retainAll(setTwo);

        if (intersectSet.isEmpty()) {
            generatedAnswer += "0";
        } else {
            generatedAnswer += intersectSet;
            generatedAnswer = generatedAnswer.replaceAll("[^\\d]", "");
        }

        questionWithAnswer.add(generatedWrittenQuestion);
        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public List<String> generateSetCardinalityQuestion() {
        String generatedWrittenQuestion = "Q. What is the cardinality of the following set: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        Set<Integer> setOne = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int randomnumber = random.nextInt(50);
            setOne.add(randomnumber);
        }

        generatedQuestion += setOne;

        int setSize = setOne.size();

        generatedAnswer += setSize;

        questionWithAnswer.add(generatedWrittenQuestion);
        questionWithAnswer.add(generatedQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }


    public String markQuestion(int enteredAnswer, int generatedAnswer) {
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
