package com.demo.shoppingcart.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.shoppingcart.model.ProductInfo;
import com.demo.shoppingcart.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RefreshScope
@RestController
@Api(value = "Shopping cart Service")
public class ShoppingCartController {

	@Value("${test.value}")
	private String value;

	@Autowired
	private ProductService productService;

	/**
	 * Will return all products
	 */
	@GetMapping(value = "/products")
	@ApiOperation(value = "Get All Products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "No Products Found") })
	public ResponseEntity<List<ProductInfo>> findAllProducts() {
		System.out.println("**********************" + value + "*******************************");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		List<ProductInfo> products = productService.list();
		return new ResponseEntity<List<ProductInfo>>(products, HttpStatus.OK);
	}

	/**
	 * Method used to return a product specific to a code
	 */
	@GetMapping(value = "/product/{code}")
	@ApiOperation(value = "Search Products by providing Product code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Product with ID not found !!") })
	public ResponseEntity<ProductInfo> findProductByCode(@PathVariable String code) {
		Optional<ProductInfo> product_opt = Optional.ofNullable(productService.findByCode(code));
		ProductInfo productInfo = product_opt
				.orElseThrow(() -> new IllegalArgumentException(String.format("Product with %s code not found", code)));
		return new ResponseEntity<ProductInfo>(productInfo, HttpStatus.OK);
	}

	/**
	 * Filters products based on the name and price.
	 */
	@GetMapping(value = "/filterProduct")
	@ApiOperation(value = "Filter products based on name and price")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Product with ID not found !!") })
	public ResponseEntity<List<ProductInfo>> filterProducts(@RequestParam String name, @RequestParam double price) {
		Optional<List<ProductInfo>> products_optional = Optional.ofNullable(productService.filter(name, price));
		Function<List<ProductInfo>, List<ProductInfo>> function = (a) -> {
			if (a.size() == 0) {
				return null;
			}
			return a;
		};
		List<ProductInfo> products = products_optional.map(function)
				.orElseThrow(() -> new IllegalArgumentException("No products matches the search critera."));
		return new ResponseEntity<List<ProductInfo>>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/test")
	public String test() {
		return "TEST";
	}

}
