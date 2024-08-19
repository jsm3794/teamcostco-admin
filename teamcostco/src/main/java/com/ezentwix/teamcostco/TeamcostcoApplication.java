package com.ezentwix.teamcostco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TeamcostcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamcostcoApplication.class, args);
	}

	@RestController
	class HelloWorld {
		@GetMapping("/")
		public String getMethodName() {
			return "Hello";
		}
	}
}
