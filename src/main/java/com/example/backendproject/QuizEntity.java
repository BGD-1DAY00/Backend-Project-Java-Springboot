package com.example.backendproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private String quizQuestion;

    private String quizAnswer;
    private String applicant;
    private boolean finished;
    private String grade;

    public QuizEntity(String quizQuestion, String quizAnswer, String applicant, boolean finished, String grade) {
        this.quizQuestion = quizQuestion;
        this.quizAnswer = quizAnswer;
        this.applicant = applicant;
        this.finished = finished;
        this.grade = grade;
    }

    public QuizEntity() {}

    public Long getId() {
        return Id;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String question) {
        this.quizQuestion = question;
    }

    public String getQuizAnswer() { return quizAnswer; }

    public void setQuizAnswer(String quizAnswer) { this.quizAnswer = quizAnswer; }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
