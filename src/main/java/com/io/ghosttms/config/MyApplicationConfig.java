package com.io.ghosttms.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.io.ghosttms.security.JWTAuthenticationEntryPoint;
import com.io.ghosttms.security.JWTAuthenticationFilter;
import com.io.ghosttms.service.JWTFilterService;

@Configuration
public class MyApplicationConfig {
	@Autowired
	private JWTAuthenticationFilter filter;
	@Autowired
	private JWTFilterService userService;
	@Autowired
	private JWTAuthenticationEntryPoint point;
	
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
		
				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/user/cart/**", "/user/wishlist/**").hasAuthority("USER")
//						.requestMatchers("/user/home/admin/**", "/user/book/delete").hasAuthority("ADMIN")
						.requestMatchers("/user/home/**").authenticated()
						.requestMatchers("/user/**", "/**").permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}


	@Bean
	 AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
