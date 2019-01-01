package com.modzo.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Index {

    @GetMapping("/")
    ModelAndView redirectToSwagger() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

}