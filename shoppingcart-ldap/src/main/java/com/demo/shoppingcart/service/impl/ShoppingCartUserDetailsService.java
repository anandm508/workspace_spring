package com.demo.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.shoppingcart.entity.User;
import com.demo.shoppingcart.model.UserPrincipal;
import com.demo.shoppingcart.repository.UserRepository;

@Service
public class ShoppingCartUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userRepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new UserPrincipal(user);
	}

}
