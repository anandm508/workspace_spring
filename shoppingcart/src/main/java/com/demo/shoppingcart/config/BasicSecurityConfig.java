package com.demo.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

//@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/h2-console/**").hasRole("ADMIN").antMatchers("/products/**")
				.hasRole("USER").antMatchers("/product/**").hasRole("USER").antMatchers("/filterProducts/**").hasRole("USER").antMatchers("/h2-console/**").hasRole("ADMIN").
				antMatchers("/manage/**").hasRole("SUPERUSER").and().httpBasic().and().logout()
	            .permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
		//Start:Used to enable h2-console
		http.csrf().disable();
		http.headers().frameOptions().disable();
		//End:
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and()
        .withUser("admin").password("password").roles("ADMIN").and()
        .withUser("su").password("password").roles("SUPERUSER");
	}	
}
