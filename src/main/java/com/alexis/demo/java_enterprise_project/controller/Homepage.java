package com.alexis.demo.java_enterprise_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Homepage {

    @GetMapping
    public String welcome(){
        return "homepage";
    }
}
