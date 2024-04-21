package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model;

public class QuestionMarker {
    public String markQuestion(int enteredAnswer, int generatedAnswer, String formattedAnswer) {
        String response = "";

        if (enteredAnswer == generatedAnswer) {
            response += "Well Done, the solution you submitted was the correct answer";
        } else {
            response += "Unfortunately, the solution you submitted was an incorrect answer, the correct answer is: "
                    + formattedAnswer;
        }

        return response;
    }
}
