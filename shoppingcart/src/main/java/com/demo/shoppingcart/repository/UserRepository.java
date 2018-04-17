package com.demo.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.shoppingcart.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
	User findByUsername(String username);
}
