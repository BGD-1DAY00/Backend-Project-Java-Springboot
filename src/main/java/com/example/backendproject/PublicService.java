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

    //key value pair
    private HashMap<UUID, Long> tokenMap;


    @Autowired
    public PublicService(@NonNull UserRespository userRespository){
        this.userRespository = userRespository;
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
}
