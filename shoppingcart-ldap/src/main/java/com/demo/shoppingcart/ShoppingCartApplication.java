package com.demo.shoppingcart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories("com.demo.shoppingcart.repository")
public class ShoppingCartApplication implements CommandLineRunner {
		
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
}
