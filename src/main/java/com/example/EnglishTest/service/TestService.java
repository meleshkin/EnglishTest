package com.example.EnglishTest.service;

import com.example.EnglishTest.model.Answer;
import com.example.EnglishTest.model.Question;
import com.example.EnglishTest.model.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    private List<String> answerKeys = Arrays.asList("A", "B", "C", "D");

    public Test createTest(Test newTest) {
        int questionsQty = 5;
        int answersQty = 4;
        List<Question> questions = new ArrayList<>(questionsQty);
        for (int i=0; i<questionsQty; i++) {
            Question q = new Question();
            q.setText("Question #" + i+1);

            List<Answer> answers = new ArrayList<>(answersQty);
            for (int j=0; j<answersQty; j++) {
                Answer a = new Answer();
                a.setAnswerKey(answerKeys.get(j));
                a.setAnswerValue("Answer " + a.getAnswerKey());
                a.setCorrect(j == 0);
                answers.add(a);
            }

            q.setAnswers(answers);
            questions.add(q);
        }

        newTest.setQuestions(questions);
        return testRepository.save(newTest);
    }

    public Test getById(Long id) {
        return testRepository.findById(id).get();
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
