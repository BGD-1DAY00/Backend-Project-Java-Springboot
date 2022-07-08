package com.example.backendproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublicService {

    private UserRespository userRespository;
    private QuizRepository quizRepository;

    //key value pair
    private HashMap<UUID, Long> tokenMap;


    @Autowired
    public PublicService(@NonNull UserRespository userRespository, QuizRepository quizRepository){
        this.userRespository = userRespository;
        this.quizRepository = quizRepository;
        this.tokenMap = new HashMap<>();
    }
    //Who is logged in?
    // token: usually stored in our browser, we are not
    // in every interaction we need to send the token and receive the token

    // token is disposed, my  apologies


    public UUID AddUser( UserEntity cred){
            //if they exist
                // reassign the role of the user, if they signed in as a recruiter the first time...
                // and then the second time sign in as an admin then we also set that value to true
        Optional<UserEntity> createdUser = userRespository.findByUsername(cred.getUsername());
        if(createdUser.isPresent()) {
            if(cred.getRole().equals("applicant"))
                createdUser.get().setApplicant(true);
            if(cred.getRole().equals("recruiter"))
                createdUser.get().setRecruiter(true);
            if(cred.getRole().equals("admin"))
                createdUser.get().setAdmin(true);
            final UUID token = UUID.randomUUID();
            // key is our token : value is our id of type long
            tokenMap.put(token, userRespository.findByUsername(createdUser.get().getUsername()).get().getId());
            userRespository.save(createdUser.get());
            return token;
        }
        //john : applicant: true
        else {
            //if they don't exist
            // set the role that they have defined
            UserEntity user = new UserEntity(cred.getUsername(), cred.getPassword(), cred.getRole());
            if(user.getRole().equals("applicant"))
                user.setApplicant(true);
            if(user.getRole().equals("recruiter"))
                user.setRecruiter(true);
            if(user.getRole().equals("admin"))
                user.setAdmin(true);
            userRespository.save(user);
            final UUID token = UUID.randomUUID();
            tokenMap.put(token, userRespository.findByUsername(user.getUsername()).get().getId());
            return token;
        }

    }

    public void AdminDeleteUser(String user) {
        Optional<UserEntity> result = userRespository.findByUsername(user);
        if (result.isPresent()) {
            userRespository.deleteById(result.get().getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public void editUser(UserEntity user, String username){
         userRespository.findByUsername(username).map(e -> {
                e.setUsername(user.getUsername());
                e.setPassword(user.getPassword());
                e.setApplicant(user.getApplicant());
                e.setRecruiter(user.getRecruiter());
                e.setAdmin(user.getAdmin());
                return userRespository.save(e);
            });
    }



    public List<UserEntity> displayUserList () {
        List<UserEntity> userList = (List<UserEntity>) userRespository.findAll();
        return userList;
    }




    public void AdminAddUser(UserEntity cred, String token) {
        Optional<UserEntity> createdUser = userRespository.findByUsername(cred.getUsername());
        if (createdUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            UserEntity user = new UserEntity(cred.getUsername(), cred.getPassword(), null);
            user.setApplicant(cred.getApplicant());
            user.setRecruiter(cred.getRecruiter());
            user.setAdmin(cred.getAdmin());
            userRespository.save(user);
        }

    }

    public void CreateQuiz(QuizEntity quiz) {
        Optional<QuizEntity> createdQuiz = quizRepository.findByQuizQuestionAndApplicant(quiz.getQuizQuestion(), quiz.getApplicant());
        if (createdQuiz.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } else {
            QuizEntity newQuiz = new QuizEntity(quiz.getQuizQuestion(), quiz.getApplicant(), quiz.isFinished(), quiz.getGrade());
            quizRepository.save(newQuiz);
        }
    }

    public List<QuizEntity> displayQuizList () {
        List<QuizEntity> quizList = (List<QuizEntity>) quizRepository.findAll();
        return quizList;
    }

    public void editQuiz(QuizEntity quizObj, Long id) {
        quizRepository.findById(id).map(e -> {
            e.setQuizQuestion(quizObj.getQuizQuestion());
            e.setApplicant(quizObj.getApplicant());
            e.setGrade(quizObj.getGrade());
            e.setFinished(quizObj.isFinished());
            return quizRepository.save(e);
        });
    }

    public void deleteQuiz (Long id) {
        Optional<QuizEntity> result = quizRepository.findById(id);
        if (result.isPresent()) {
            quizRepository.deleteById(result.get().getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
