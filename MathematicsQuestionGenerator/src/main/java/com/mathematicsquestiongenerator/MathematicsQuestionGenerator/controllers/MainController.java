package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.CustomUserDetails;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.User;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository.UserRepository;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping({"/", "/homepage"})
    public String homepage() {
        return "homepage";
    }

    @GetMapping({"/explore"})
    public String explore() {
        return "explore";
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
