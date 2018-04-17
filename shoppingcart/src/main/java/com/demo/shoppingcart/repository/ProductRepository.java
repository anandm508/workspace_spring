package com.demo.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.shoppingcart.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String>{
	Product findByCode(String code);
}
