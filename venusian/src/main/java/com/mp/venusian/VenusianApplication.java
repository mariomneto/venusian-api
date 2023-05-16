package com.mp.venusian;

import com.mp.venusian.configs.FirebaseInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
public class VenusianApplication {
	public static void main(String[] args) {
		FirebaseInitializer firebaseInitializer = new FirebaseInitializer();
		firebaseInitializer.initialize();
		SpringApplication.run(VenusianApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Olá Mundo!";
	}
}
