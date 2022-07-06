package com.example.backendproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicService {

    private UserRespository userRespository;

    @Autowired
    public PublicService(@NonNull UserRespository userRespository){
        this.userRespository = userRespository;
    }

    public void AddUser( UserEntity cred){
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
            userRespository.save(createdUser.get());
        }
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
        }
    }

//    private void setRoles(UserEntity user, String role) {
//        if(role.equals("applicant"))
//            user.setApplicant(true);
//        if(role.equals("recruiter"))
//            user.setRecruiter(true);
//        if(role.equals("admin"))
//            user.setAdmin(true);
//    }
}
