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
@Order(2)
public class SecurityConfiguration2 {
	
	@Autowired
	private StallDetailsService stallDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder2() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
		http.
				authorizeHttpRequests(authorize->authorize
						.requestMatchers("/loginAsStall","/index").permitAll()
						.anyRequest().authenticated()
						)
				.formLogin(
						form->form
						.loginPage("/loginAsStall")
						.loginProcessingUrl("/loginAsStall")
						.defaultSuccessUrl("/stallpage")
						.permitAll()
						);
		return http.build();
	}
	
	public void configureGloba2(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(stallDetailsService)
		.passwordEncoder(passwordEncoder2());
	}
}
*/