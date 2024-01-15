package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping({"/adminhomepage"})
    public String adminhomepage() {
        return "adminhomepage";
    }

}
