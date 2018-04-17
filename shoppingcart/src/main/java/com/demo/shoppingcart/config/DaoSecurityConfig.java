package com.demo.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.demo.shoppingcart.service.impl.ShoppingCartUserDetailsService;

//@EnableWebSecurity
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ShoppingCartUserDetailsService userDetailsService;
	
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
	protected void configure(AuthenticationManagerBuilder auth)
	  throws Exception {
	    auth.authenticationProvider(authenticationProvider());
	}
	 
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    return authProvider;
	}
}
