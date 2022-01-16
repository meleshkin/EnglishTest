package com.example.EnglishTest.service;

import com.example.EnglishTest.model.Answer;
import com.example.EnglishTest.model.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getById(Long id) {
        return questionRepository.findById(id).get();
    }

    public Question update(Question question) {
        boolean hasCorrectAnswer = false;
        for (Answer a : question.getAnswers()) {
            if (a.isCorrect()) {
                hasCorrectAnswer = true;
                break;
            }
        }
        if (hasCorrectAnswer) {
            return questionRepository.save(question);
        } else {
            questionRepository.delete(question);
            return null;
        }
    }
}
