package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetsQuestions {
    private RandomNumberGenerator randomNumberGenerator
            = new RandomNumberGenerator();

    public List<String> generateSetUnionQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();

        String writtenQuestion;
        String generatedAnswer = "";

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

        Set<Integer> unionSet = new HashSet<>(setOne);
        unionSet.addAll(setTwo);

        writtenQuestion = "Let Set A = " + setOne + " and Set B = " + setTwo + ". Calculate A ∪ B";
        generatedAnswer += unionSet;
        generatedAnswer = generatedAnswer.replaceAll("[^\\d]", "");

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public List<String> generateSetIntersectQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();

        String writtenQuestion;
        String generatedAnswer = "";

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

        Set<Integer> intersectSet = new HashSet<>(setOne);
        intersectSet.retainAll(setTwo);

        writtenQuestion = "Let Set A = " + setOne + " and Set B = " + setTwo + ". Calculate A ∩ B";

        if (intersectSet.isEmpty()) {
            generatedAnswer += "0";
        } else {
            generatedAnswer += intersectSet;
            generatedAnswer = generatedAnswer.replaceAll("[^\\d]", "");
        }

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

    public List<String> generateSetCardinalityQuestion() {
        List<String> questionWithAnswer = new ArrayList<>();

        String writtenQuestion;
        String generatedAnswer = "";

        Set<Integer> setOne = new HashSet<>();

        int randomSetSize = randomNumberGenerator.generateRandomNumber(10);

        if (randomSetSize == 0) {
            randomSetSize += 1;
        }

        for (int i = 0; i < randomSetSize; i++) {
            int randomnumber = randomNumberGenerator.generateRandomNumber(50);
            setOne.add(randomnumber);
        }

        int setSize = setOne.size();

        writtenQuestion = "Let Set A = " + setOne + ". Calculate the cardinality of A";
        generatedAnswer += setSize;

        questionWithAnswer.add(writtenQuestion);
        questionWithAnswer.add(generatedAnswer);

        return questionWithAnswer;
    }

}
