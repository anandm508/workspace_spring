package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication(exclude = JmsAutoConfiguration.class)
public class AutoConfigurationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoConfigurationDemoApplication.class, args);

	}
}
