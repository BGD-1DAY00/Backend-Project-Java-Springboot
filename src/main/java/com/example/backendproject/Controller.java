package com.example.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class Controller {

    private PublicService publicService;

    @Autowired
    public Controller(@NonNull PublicService publicService){
        this.publicService = publicService;
    }

    @GetMapping("/")
    public String j(){
        return "here";
    }

    @PostMapping(
            value ="/login", consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public void login(@RequestBody UserEntity user){
        publicService.AddUser(user);
    }
}
