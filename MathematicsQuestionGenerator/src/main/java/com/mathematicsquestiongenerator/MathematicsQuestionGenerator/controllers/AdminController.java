package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.Topics;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.TopicsRepository;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/adminhomepage"})
    public String adminhomepage(Model model) {
        model.addAttribute("topics", topicsRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        return "adminhomepage";
    }

    @GetMapping({"/admintopics"})
    public String admintopics() {
        return "admintopics";
    }

    @PostMapping({"/insertTopic"})
    public String insertTopic(Topics topics) {
        topicsRepository.save(topics);

        return "admintopics";
    }

}
