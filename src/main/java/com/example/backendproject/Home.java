package com.example.backendproject;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Home {
    @GetMapping("/")
    public String j(){
        return "here";
    }

    @PostMapping("/login")
    public void login(@RequestBody Object j){
        System.out.println(j.toString());
    }
}
