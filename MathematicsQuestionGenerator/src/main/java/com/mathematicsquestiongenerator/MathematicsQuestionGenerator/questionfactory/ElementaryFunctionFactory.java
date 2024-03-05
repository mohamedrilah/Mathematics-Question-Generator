package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

public class ElementaryFunctionFactory implements QuestionFactory {
    @Override
    public Question createQuestion() {
        return new ElementaryFunctionQuestions();
    }
}
