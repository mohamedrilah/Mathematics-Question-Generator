package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

import java.util.*;

public class RandomNumberGenerator {
    private Random random;

    public RandomNumberGenerator() {
        random = new Random();
    }

    public int generateRandomNumber(int upperBound) {
        int randomNumber = random.nextInt(upperBound);

        return randomNumber;
    }

}
