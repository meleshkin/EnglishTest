package com.example.EnglishTest.controller;

import com.example.EnglishTest.model.Question;
import com.example.EnglishTest.model.Test;
import com.example.EnglishTest.service.QuestionService;
import com.example.EnglishTest.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminTestController {

    private final TestService testService;
    private final QuestionService questionService;

    public AdminTestController(TestService testService, QuestionService questionService) {
        this.testService = testService;
        this.questionService = questionService;
    }

    @GetMapping("/admin/")
    @SuppressWarnings("unused")
    public String handleMain(Model model) {
        fillModelWithAllTests(model);
        return "main.html";
    }

    @GetMapping("/admin/test/new")
    @SuppressWarnings("unused")
    public String newTest(Model model) {
        model.addAttribute("test", new Test());
        return "createTest.html";
    }

    @PostMapping("/admin/test/create")
    @SuppressWarnings("unused")
    public String createTest(@ModelAttribute Test test, Model model) {
        testService.createTest(test);
        fillModelWithAllTests(model);
        return "main.html";
    }


    @GetMapping("/admin/test/edit/{id}")
    @SuppressWarnings("unused")
    public String editTest(@PathVariable long id, Model model) {
        Test test = testService.getById(id);
        model.addAttribute("test", test);
        return "editTest.html";
    }

    @PostMapping("/admin/test/edit")
    @SuppressWarnings("unused")
    public String editTest(@ModelAttribute Test test, Model model) {
        testService.saveTest(test);
        fillModelWithAllTests(model);
        return "main.html";
    }

    @GetMapping("/admin/question/edit/{id}/testId/{testId}")
    @SuppressWarnings("unused")
    public String editQuestion(@PathVariable long id, @PathVariable long testId,  Model model) {
        Question question = questionService.getById(id);
        question.setTestId(testId);
        model.addAttribute("question", question);
        return "editQuestion.html";
    }

    @PostMapping("/admin/question/edit")
    @SuppressWarnings("unused")
    public String editQuestion(@ModelAttribute Question question, Model model) {
        long testId = question.getTestId();
        questionService.update(question);
        Test testWithNewQuestion = testService.getById(testId);
        model.addAttribute("test", testWithNewQuestion);
        return "editTest.html";
    }

    @GetMapping("/admin/test/addQuestion/{testId}")
    @SuppressWarnings("unused")
    public String addQuestion(@PathVariable long testId, Model model) {
        Test testWithNewQuestion = testService.addQuestion(testId);
        model.addAttribute("test", testWithNewQuestion);
        return "editTest.html";

    }

    @GetMapping("/admin/test/delete/{id}")
    public String deleteTest(@PathVariable long id, Model model) {
        testService.deleteTest(id);
        fillModelWithAllTests(model);
        return "main.html";
    }

    private void fillModelWithAllTests(Model model) {
        List<Test> allTests = testService.getAllTests();
        model.addAttribute("allTests", allTests);
    }
}
