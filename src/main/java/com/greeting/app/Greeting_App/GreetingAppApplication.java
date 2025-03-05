package com.greeting.app.Greeting_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
@ComponentScan(basePackages = "com.greeting.app.Greeting_App")
public class GreetingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingAppApplication.class, args);
		System.out.println("Greetings");
	}
}
