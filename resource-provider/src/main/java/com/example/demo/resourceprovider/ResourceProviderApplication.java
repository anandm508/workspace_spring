package com.example.demo.resourceprovider;

import java.util.UUID;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceProviderApplication extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource-server-rest-api";
	private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
	private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
	private static final String SECURED_PATTERN = "/secured/**";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers(SECURED_PATTERN).and().authorizeRequests()
				.antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE).anyRequest()
				.access(SECURED_READ_SCOPE);
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceProviderApplication.class, args);
	}

	@RestController
	@RequestMapping("/secured/")
	static class Controller {
		@GetMapping("id")
		public String getID() {
			return "success (id: " + UUID.randomUUID().toString().toUpperCase() + ")";
		}
		
		@PostMapping("updateId")
		@PreAuthorize("hasRole('ADMIN')")
		public String setID(@RequestParam String id) {
			return "success (id: " + id + ")";
		}
	}
	
	@Bean
	public CustomUserInfoTokenServices userInfoTokenServices(ResourceServerProperties sso,
			UserInfoRestTemplateFactory restTemplateFactory,
			ObjectProvider<AuthoritiesExtractor> authoritiesExtractor,
			ObjectProvider<PrincipalExtractor> principalExtractor) {
		
		CustomUserInfoTokenServices services = new CustomUserInfoTokenServices(
				sso.getUserInfoUri(), sso.getClientId());
		services.setRestTemplate(restTemplateFactory.getUserInfoRestTemplate());
		services.setTokenType(sso.getTokenType());
		
		final AuthoritiesExtractor authoritiesExt = authoritiesExtractor.getIfAvailable();
		final PrincipalExtractor principalExt = principalExtractor.getIfAvailable();
		
		if (authoritiesExt != null) {
			services.setAuthoritiesExtractor(authoritiesExt);
		}
		if (principalExt != null) {
			services.setPrincipalExtractor(principalExt);
		}
		return services;
	}

}
