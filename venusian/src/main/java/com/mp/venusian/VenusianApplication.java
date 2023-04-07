package com.mp.venusian;

import com.mp.venusian.configs.FirebaseInitialize;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VenusianApplication {
	public static void main(String[] args) {
		FirebaseInitialize firebaseInitialize = new FirebaseInitialize();
		firebaseInitialize.initialize();
		SpringApplication.run(VenusianApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Olá Mundo!";
	}
}
