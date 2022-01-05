package com.example.EnglishTest.controller;

import com.example.EnglishTest.model.Answer;
import com.example.EnglishTest.model.Question;
import com.example.EnglishTest.model.Test;
import com.example.EnglishTest.service.TestRepository;
import com.example.EnglishTest.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final TestService testService;

    public MainController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    @SuppressWarnings("unused")
    public String handleMain(Model model) {
        List<Test> allTests = testService.getAllTests();
        model.addAttribute("allTests", allTests);
        return "main.html";
    }

    @GetMapping("/test/new")
    @SuppressWarnings("unused")
    public String newTest(Model model) {
        model.addAttribute("test", new Test());
        return "createTest.html";
    }

    @PostMapping("/test/create")
    @SuppressWarnings("unused")
    public String createTest(@ModelAttribute Test test) {
        testService.createTest(test);
        return "main.html";
    }


    @GetMapping("/test/edit/{id}")
    @SuppressWarnings("unused")
    public String editTest(@PathVariable long id, Model model) {
        Test test = testService.getById(id);
        model.addAttribute("test", test);
        return "editTest.html";
    }

    @PostMapping("/test/edit")
    @SuppressWarnings("unused")
    public String editTest(@ModelAttribute Test test) {
        System.out.println(test);
        return "editTest.html";
    }

    @GetMapping("/question/edit/{id}")
    public String editQuestion(@PathVariable long id, Model model) {
        // todo: impl. it
        return "main.html";
    }

    @GetMapping("/test/edit2")
    public String testEdit(Model model) {
        Question q = new Question();
        q.setAnswers(new ArrayList<>());
        model.addAttribute("question", q);
        return "editTestOld.html";
    }

    @PostMapping("/test/edit1")
    public String testEdit1(@ModelAttribute Question question) {
        System.out.println(question);
        return "editTestOld.html";
    }
}
