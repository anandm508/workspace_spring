package com.demo.shoppingcart.controller.advice;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages="com.demo.shoppingcart.controller")
public class ShoppingCartControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> productNotFoundException(final Exception e) {
		final String message = Optional.ofNullable(e.getMessage()).orElse(e.getClass().getSimpleName());
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
