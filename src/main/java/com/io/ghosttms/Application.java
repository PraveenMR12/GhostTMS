package com.io.ghosttms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	public void run1() {
		System.out.println("Post Construct");
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("command line runner");
		
	}

}
