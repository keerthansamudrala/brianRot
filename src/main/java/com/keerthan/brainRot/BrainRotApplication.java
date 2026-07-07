package com.keerthan.brainRot;

import com.keerthan.brainRot.model.User;
import com.keerthan.brainRot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrainRotApplication{

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(BrainRotApplication.class, args);
	}



}
