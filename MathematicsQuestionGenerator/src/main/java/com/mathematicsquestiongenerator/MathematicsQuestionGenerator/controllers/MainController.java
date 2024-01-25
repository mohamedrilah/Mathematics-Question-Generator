package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.CustomUserDetails;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.QuestionResponse;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.RandomQuestionGenerator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private RandomQuestionGenerator randomNumberGenerator = new RandomQuestionGenerator();

    private List<String> generatedQuestionList = new ArrayList<>();

    private int answer;
    private int enteredAnswer;

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

        randomNumberGenerator = new RandomQuestionGenerator();

        generatedQuestionList = randomNumberGenerator.generateAdditionQuestion(15, 2);

        String answerFromList = "";

        for (String s: generatedQuestionList) {
            if (s.contains("+")) {
            } else {
                answer = Integer.parseInt(s);
                answerFromList = s;
            }
        }

        generatedQuestionList.remove(answerFromList);

        model.addAttribute("questionresponse", new QuestionResponse());

        model.addAttribute("randomValue",
                generatedQuestionList);

        return "topicquiz";
    }

    @PostMapping({"/checkanswer"})
    public String checkanswer(@ModelAttribute QuestionResponse questionresponse) {

        enteredAnswer = questionresponse.getEnteredSolution();

        String response = randomNumberGenerator.markAdditionQuestion(enteredAnswer, answer);

        String output = "";

        for (String s: generatedQuestionList) {
            output += "Question: " + s + ": " + response;
        }

        System.out.println(output);

        return "redirect:topicquiz";
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

    @GetMapping({"/myaccount"})
    public String myaccount(Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        model.addAttribute("user", customUserDetailsService.loadUserByUsername(username));

        return "myaccount";
    }

}
