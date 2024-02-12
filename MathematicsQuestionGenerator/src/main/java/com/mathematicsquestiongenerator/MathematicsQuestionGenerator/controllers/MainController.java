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

    private List<String> generatedFeedbackList = new ArrayList<>();

    private int answer;

    private int answerStreak;

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

    @GetMapping({"/quizselection"})
    public String quizselection() {
        answerStreak = 0;

        return "quizselection";
    }

    @GetMapping({"/testmode"})
    public String testmode(Model model) {

        return "testmode";
    }

    @GetMapping({"/practicemode"})
    public String practicemode(Model model) {
        randomNumberGenerator = new RandomQuestionGenerator();

//        generatedQuestionList = randomNumberGenerator.generateAdditionQuestion(15);
//        generatedQuestionList = randomNumberGenerator.generateBinaryToDenaryQuestion();

        generatedQuestionList = randomNumberGenerator.generateDenaryToBinaryQuestion();

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

        return "feedback";
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
