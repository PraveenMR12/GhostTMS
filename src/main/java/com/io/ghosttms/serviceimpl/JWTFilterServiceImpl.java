package com.io.ghosttms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.io.ghosttms.repository.UserRepository;
import com.io.ghosttms.security.CustomUserDetails;
import com.io.ghosttms.service.JWTFilterService;


@Service
public class JWTFilterServiceImpl implements JWTFilterService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				
				return new CustomUserDetails(userRepo.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found")));
			}
		};
	}

	
	
}
