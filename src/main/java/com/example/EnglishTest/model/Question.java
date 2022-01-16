package com.example.EnglishTest.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @SequenceGenerator(name="id_seq", sequenceName="id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_seq")
    private long id;

    private String text;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_fk")
    private List<Answer> answers;

    @Transient
    private long testId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }
}
