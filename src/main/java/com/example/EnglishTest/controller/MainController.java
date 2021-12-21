package com.example.EnglishTest.controller;

import com.example.EnglishTest.model.Test;
import com.example.EnglishTest.service.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final TestRepository testRepository;

    public MainController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping("/")
    @SuppressWarnings("unused")
    public String handleMain() {
        List<Test> allTests = testRepository.findAll();
        allTests.forEach(test -> System.out.println(test.getName()));
        return "main.html";
    }
}
