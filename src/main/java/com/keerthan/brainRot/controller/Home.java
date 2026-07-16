package com.keerthan.brainRot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @RequestMapping("/")
    public String greet(){
        return "Welcoming all cockroaches to BrainRot";
    }

}
