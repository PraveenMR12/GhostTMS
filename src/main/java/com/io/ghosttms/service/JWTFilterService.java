package com.io.ghosttms.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface JWTFilterService {

		UserDetailsService userDetailsService();

}
