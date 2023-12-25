package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping({"/", "/homepage"})
    public String homepage() {
        return "homepage";
    }

    @GetMapping({"explore"})
    public String explore() {
        return "explore";
    }

    @GetMapping({"/aboutus"})
    public String aboutus() {
        return "aboutus";
    }

    @GetMapping({"/signin"})
    public String signin() {
        return "signin";
    }

    @GetMapping({"/signup"})
    public String signup() {
        return "signup";
    }
}
