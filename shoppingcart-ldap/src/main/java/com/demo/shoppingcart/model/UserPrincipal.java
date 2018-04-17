package com.demo.shoppingcart.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.shoppingcart.entity.User;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 3619850485965486338L;

	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserPrincipal() {
		System.out.println("No arg constructor");
	}
	
	public UserPrincipal(final User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		//GrantedAuthority auth = ()->user.getAuthority();
		String authority = user.getAuthority();
		GrantedAuthority auth = ()-> authority;
		authorities = new ArrayList<>(1);
		authorities.add(auth);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
