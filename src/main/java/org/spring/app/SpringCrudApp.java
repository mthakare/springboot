package org.spring.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({""})
public class SpringCrudApp {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringCrudApp.class, args);

	}
}
