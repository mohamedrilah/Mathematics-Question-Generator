package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.CustomUserDetails;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.RandomNumberGenerator;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.User;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.TopicsRepository;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.UserRepository;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TopicsRepository topicsRepository;

    @GetMapping({"/", "/homepage"})
    public String homepage() {
        return "homepage";
    }

    @GetMapping({"/explore"})
    public String explore(Model model) {
        model.addAttribute("topics", topicsRepository.findAll());
        return "explore";
    }

    @GetMapping({"/problems"})
    public String problems(Model model) {
        model.addAttribute("topics", topicsRepository.findAll());
        return "problems";
    }

    @GetMapping({"/topicquiz"})
    public String topicquiz(Model model) {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<Integer> numberList = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i < 2; i++) {
            int randomNumber = randomNumberGenerator.generateRandomNumber(10);

            answer += randomNumber;

            numberList.add(randomNumber);
        }

        numberList.add(answer);

        model.addAttribute("randomValue", numberList);

        return "topicquiz";
    }

    @GetMapping({"/aboutus"})
    public String aboutus() {
        return "aboutus";
    }

    @GetMapping({"/login"})
    public String login() {
        return "signin";
    }

    @GetMapping({"/register"})
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/registerUser")
    public String registerUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("Student");

        userRepository.save(user);

        return "signin";
    }

    @GetMapping("/myaccount")
    public String myaccount(Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        model.addAttribute("user", customUserDetailsService.loadUserByUsername(username));

        return "myaccount";
    }

}
