package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

public class NumeralSystemsFactory implements QuestionFactory{
    @Override
    public Question createQuestion() {
        return new NumeralSystemsQuestions();
    }
}
