package com.demo.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Value("${ldap.urls}")
	private String ldapUrls;

	@Value("${ldap.base.dn}")
	private String ldapBaseDn;

	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;

	@Value("${ldap.password}")
	private String ldapPrincipalPassword;

	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;

	@Value("${ldap.embedded}")
	private String useEmbeddedLdap;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests().antMatchers("/h2-console/**")
				.hasAnyRole("ADMIN", "SCIENTISTS").antMatchers("/products/**").hasRole("USER")
				.antMatchers("/product/**").hasRole("USER").antMatchers("/filterProducts/**").hasRole("USER")
				.antMatchers("/manage/**").hasRole("SUPERUSER").and().httpBasic().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler).and().sessionManagement()
				// Don't maintain session in server
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Start:Used to enable h2-console
		http.csrf().disable();
		http.headers().frameOptions().disable();
		// End:
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (Boolean.parseBoolean(useEmbeddedLdap)) {
			auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})")
					.groupSearchBase("ou=groups").groupSearchFilter("(member={0})").contextSource()
					.root("dc=baeldung,dc=com").ldif("classpath:users.ldif");
		} else {
			auth.ldapAuthentication().contextSource().url(ldapUrls + ldapBaseDn).managerDn(ldapSecurityPrincipal)
					.managerPassword(ldapPrincipalPassword).and().userDnPatterns(ldapUserDnPattern);
		}

	}
}
