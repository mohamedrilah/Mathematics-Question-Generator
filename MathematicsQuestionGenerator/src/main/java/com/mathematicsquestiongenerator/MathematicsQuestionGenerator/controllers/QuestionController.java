package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.QuestionResponse;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.RandomQuestionGenerator;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.Topics;
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
    private RandomQuestionGenerator randomNumberGenerator = new RandomQuestionGenerator();
    private List<String> generatedQuestionList = new ArrayList<>();
    private List<String> generatedFeedbackList = new ArrayList<>();
    private int answer;
    private int answerStreak;
    private int topicid;

    @GetMapping({"/practice"})
    public String practice(Model model) {
        model.addAttribute("topics", topicsRepository.findAll());
        answerStreak = 0;
        return "practice";
    }

    @GetMapping({"/practicemode/{id}"})
    public String practicemode(@PathVariable("id") int id, Model model) {

        Topics topic = topicsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));

        String topicname = topic.getTopicname();
        topicid = topic.getId();

        randomNumberGenerator = new RandomQuestionGenerator();

        int randomNumber = randomNumberGenerator.generateRandomNumber(2);

        if (topicname.contains("Numeral Systems")) {

            if (randomNumber == 0) {
                generatedQuestionList = randomNumberGenerator.generateBinaryToDenaryQuestion();
            } else if (randomNumber == 1) {
                generatedQuestionList = randomNumberGenerator.generateDenaryToBinaryQuestion();
            }

        } else if (topicname.contains("Addition")) {
            generatedQuestionList = randomNumberGenerator.generateAdditionQuestion(15);
        }

        String writtenQuestion = generatedQuestionList.get(0);

        generatedQuestionList.remove(0);

        answer = Integer.parseInt(generatedQuestionList.get(1));

        generatedQuestionList.remove(1);

        model.addAttribute("questionresponse", new QuestionResponse());

        model.addAttribute("writtenQuestion",
                writtenQuestion);

        model.addAttribute("randomValue",
                generatedQuestionList);

        return "practicemode";
    }

    @PostMapping({"/checkanswer"})
    public String checkanswer(@ModelAttribute QuestionResponse questionresponse) {

        int enteredAnswer = questionresponse.getEnteredSolution();

        String response = randomNumberGenerator.markQuestion(enteredAnswer, answer);

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

        Topics topic = topicsRepository.findById(topicid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));

        model.addAttribute("topic", topic);

        return "feedback";
    }

}
