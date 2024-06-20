package com.example.spring_security_jsp_user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/signup").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(form -> form
					.loginPage("/signin")
					.loginProcessingUrl("/login")
					.usernameParameter("username")
					.defaultSuccessUrl("/")
					.permitAll()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.invalidateHttpSession(true)
					.logoutSuccessUrl("/")
					.permitAll()
			);
		
		return http.build();
	}
}
