package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.Topics;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private TopicsRepository topicsRepository;

    @GetMapping({"/adminhomepage"})
    public String adminhomepage() {
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
