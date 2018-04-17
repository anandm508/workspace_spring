package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignDemoApplication implements CommandLineRunner{
	private static final Logger LOGGER = LoggerFactory.getLogger(FeignDemoApplication.class);
	@Autowired
	private ShoppingCartClient shoppingCartClient;
	
	public static void main(String[] args) {
		SpringApplication.run(FeignDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final String value = "ABC";
		LOGGER.trace("doStuff needed more information - {}", value);
        LOGGER.debug("doStuff needed to debug - {}", value);
        LOGGER.info("doStuff took input - {}", value);
        
        LOGGER.warn("doStuff needed to warn - {}", value);
        LOGGER.error("doStuff encountered an error with value - {}", value);
		shoppingCartClient.findAllProducts().stream().forEach((product) ->System.out.println(product));
	}
}
