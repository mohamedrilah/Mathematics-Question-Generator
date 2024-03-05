package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

public class SetsPredicateFactory implements QuestionFactory {

    @Override
    public Question createQuestion() {
        return new SetsPredicateQuestions();
    }
}
