package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
public class HelloApplication {

	@RestController
	public class HelloApplicationController{
		
		@RequestMapping("/")
		public String test(@RequestParam("id") String id) {
			return "Hello World id=[" + id + "]";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}


