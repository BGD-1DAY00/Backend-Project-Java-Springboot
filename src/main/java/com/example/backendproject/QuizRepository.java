package com.example.backendproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends CrudRepository <QuizEntity, Long>{
    Optional<QuizEntity> findByQuizQuestionAndApplicant (String quizQuestion, String applicant);
}
