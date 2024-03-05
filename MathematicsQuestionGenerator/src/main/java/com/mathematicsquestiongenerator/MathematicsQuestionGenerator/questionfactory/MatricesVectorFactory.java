package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory;

public class MatricesVectorFactory implements QuestionFactory {
    @Override
    public Question createQuestion() {
        return new MatricesVectorQuestions();
    }
}
