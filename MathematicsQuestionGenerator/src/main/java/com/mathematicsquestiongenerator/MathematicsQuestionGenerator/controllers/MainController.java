package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping({"/", "/homepage"})
    public String homepage() {
        return "homepage";
    }
    @GetMapping({"/signin"})
    public String signin() {
        return "signin";
    }

    @GetMapping({"/aboutus"})
    public String aboutus() {
        return "aboutus";
    }

}
