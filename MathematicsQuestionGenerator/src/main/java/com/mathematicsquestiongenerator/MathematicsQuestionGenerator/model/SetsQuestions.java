package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetsQuestions {
    private RandomNumberGenerator randomNumberGenerator
            = new RandomNumberGenerator();

    public List<String> generateSetUnionQuestion() {
        String generatedWrittenQuestion = "What is the union of the following sets: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        Set<Integer> setOne = new HashSet<>();
        Set<Integer> setTwo = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int randomnumber = randomNumberGenerator.generateRandomNumber(10);
            setOne.add(randomnumber);
        }

        for (int i = 0; i < 4; i++) {
            int randomnumber = randomNumberGenerator.generateRandomNumber(10);
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
        String generatedWrittenQuestion = "What is the intersect of the following sets: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        Set<Integer> setOne = new HashSet<>();
        Set<Integer> setTwo = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int randomnumber = randomNumberGenerator.generateRandomNumber(50);
            setOne.add(randomnumber);
        }

        for (int i = 0; i < 4; i++) {
            int randomnumber = randomNumberGenerator.generateRandomNumber(50);
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
        String generatedWrittenQuestion = "What is the cardinality of the following set: ";
        String generatedQuestion = "";
        String generatedAnswer = "";
        List<String> questionWithAnswer = new ArrayList<>();

        Set<Integer> setOne = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int randomnumber = randomNumberGenerator.generateRandomNumber(50);
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

}
