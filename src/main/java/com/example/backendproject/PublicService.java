package com.example.backendproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class PublicService {

    private UserRespository userRespository;

    @Autowired
    public PublicService(@NonNull UserRespository userRespository){
        this.userRespository = userRespository;
    }

    public void AddUser( UserEntity cred){
        userRespository.save(new UserEntity(cred.getUsername(), cred.getPassword(),cred.getRole()));
    }
}
