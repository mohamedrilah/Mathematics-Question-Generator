package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.*;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.questionfactory.*;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private TopicsRepository topicsRepository;
    private QuestionMarker questionMarker = new QuestionMarker();
    private List<String> generatedQuestionList = new ArrayList<>();
    private List<String> generatedFeedbackList = new ArrayList<>();
    private int answer;
    private String formattedAnswer;
    private int answerStreak;
    private String topicname;

    @GetMapping({"/practice"})
    public String practice(Model model) {
        model.addAttribute("topics", topicsRepository.findAll());
        answerStreak = 0;
        return "practice";
    }

    @GetMapping({"/practicemode/{name}"})
    public String practicemode(@PathVariable("name") String name, Model model) {

        Topics topic = topicsRepository.findTopicByTopicname(name);
        topicname = topic.getTopicname();

        if (topicname.contains("Numeral Systems")) {
            NumeralSystemsFactory numeralSystemsFactory = new NumeralSystemsFactory();
            Question numeralSystemsQuestion = numeralSystemsFactory.createQuestion();
            generatedQuestionList = numeralSystemsQuestion.displayQuestion();

        } else if (topicname.contains("Sets & Predicate")) {
            SetsPredicateFactory setsPredicateFactory = new SetsPredicateFactory();
            Question setsPredicateQuestion = setsPredicateFactory.createQuestion();
            generatedQuestionList = setsPredicateQuestion.displayQuestion();

        } else if (topicname.contains("Elementary Functions")) {
            ElementaryFunctionFactory elementaryFunctionFactory = new ElementaryFunctionFactory();
            Question elementaryFunctionQuestion = elementaryFunctionFactory.createQuestion();
            generatedQuestionList = elementaryFunctionQuestion.displayQuestion();

        } else if (topicname.contains("Matrices & Vectors")) {
            MatricesVectorFactory matricesVectorFactory = new MatricesVectorFactory();
            Question matricesVectorQuestion = matricesVectorFactory.createQuestion();
            generatedQuestionList = matricesVectorQuestion.displayQuestion();
        }

        String question = generatedQuestionList.get(0);

        answer = Integer.parseInt(generatedQuestionList.get(1));

        formattedAnswer = generatedQuestionList.get(2);

        model.addAttribute("questionresponse", new QuestionResponse());

        model.addAttribute("writtenQuestion",
                question);

        return "practicemode";
    }

    @PostMapping({"/checkanswer"})
    public String checkanswer(@ModelAttribute QuestionResponse questionresponse) {

        String answerString = questionresponse.getEnteredSolution();

        answerString = answerString.replaceAll("[^\\d]", "");

        int enteredAnswer;

        if (answerString.isEmpty()) {
            enteredAnswer = 0;
        } else {
            enteredAnswer = Integer.parseInt(answerString);
        }

        String response = questionMarker.markQuestion(enteredAnswer, answer, formattedAnswer);

        if (response.contains("Well Done")) {
            answerStreak += 1;

        } else if (response.contains("Unfortunately")) {
            answerStreak = 0;
        }

        generatedFeedbackList.add(response);

        return "redirect:/feedback";
    }

    @GetMapping({"/feedback"})
    public String feedback(Model model) {

        model.addAttribute("question", generatedQuestionList.get(0));

        if (!generatedFeedbackList.isEmpty()) {
            model.addAttribute("response", generatedFeedbackList.get(0));

            generatedFeedbackList.remove(0);
        }

        if (answerStreak > 1) {
            String streakmessage = "Congratulations, you have an answer streak of: "
                    + answerStreak;

            model.addAttribute("answerstreak", streakmessage);

        } else if (answerStreak == 0) {
            String streakmessage = "Unfortunately, you have lost your answer streak";

            model.addAttribute("answerstreak", streakmessage);
        }

        Topics topic = topicsRepository.findTopicByTopicname(topicname);

        model.addAttribute("topic", topic);

        return "feedback";
    }

}
