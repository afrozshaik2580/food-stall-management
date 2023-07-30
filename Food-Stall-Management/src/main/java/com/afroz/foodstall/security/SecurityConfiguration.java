/*
package com.afroz.foodstall.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
		http.
				authorizeHttpRequests(authorize->authorize
						.requestMatchers("/loginAsUser","/index").permitAll()
						.anyRequest().authenticated()
						)
				.formLogin(
						form->form
						.loginPage("/loginAsUser")
						.loginProcessingUrl("/loginAsUser")
						.defaultSuccessUrl("/userpage")
						.permitAll()
						);
		return http.build();
	}
	
	public void configureGlobal1(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder1());
	}
}
*/