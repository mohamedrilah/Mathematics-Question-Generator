package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.Topics;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.User;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.TopicsRepository;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping({"/deleteTopic/{id}"})
    public String deleteTopic(@PathVariable("id") int id) {
        Topics topic = topicsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));

        topicsRepository.delete(topic);

        return "redirect:/adminhomepage";
    }

    @GetMapping({"/deleteUser/{id}"})
    public String deleteUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));

        userRepository.delete(user);

        return "redirect:/adminhomepage";
    }

}
