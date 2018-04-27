package com.example.demo.identityprovider;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableResourceServer
public class OAuth2Config {

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthServer extends AuthorizationServerConfigurerAdapter {
		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("readonlyclient").secret("{noop}secret")
					.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("read").and()
					.withClient("readwriteclient").secret("{noop}secret")
					.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("read", "write");
		}
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

}