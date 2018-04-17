package com.demo.shoppingcart;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories("com.demo.shoppingcart.repository")
public class ShoppingCartApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(UUID.randomUUID().toString());
	}
}
