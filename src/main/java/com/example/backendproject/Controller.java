package com.example.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            value ="/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}

    )
    public UUID login(@RequestBody UserEntity user){
        return publicService.AddUser(user);
    }
}