package com.example.demo;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-1")
public interface ShoppingCartClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public List<ProductInfo> findAllProducts();

}
