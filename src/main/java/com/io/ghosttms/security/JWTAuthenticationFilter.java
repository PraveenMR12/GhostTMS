package com.io.ghosttms.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.io.ghosttms.service.JWTFilterService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	
	@Autowired
  private JWTHelper jwtHelper;
  
  @Autowired
  private JWTFilterService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

	  	String username = null;
		String jwtToken = null;
			if(request.getCookies()!=null)
			{
				Cookie[] cookies =request.getCookies();
				
				for(Cookie cookie: cookies)
				{
					if(cookie.getName().equals("token")==true)
					{
						jwtToken = cookie.getValue().toString();
					}
				}
			}
		
			      
		if(jwtToken == null)
		{
		  filterChain.doFilter(request, response);
		  return;
		}
		try {
			username = this.jwtHelper.extractUserName(jwtToken);
		} catch (ExpiredJwtException e) {
			Cookie cookie = new Cookie("token", null);
			cookie.setMaxAge(0);
			cookie.setPath("/user");
			response.addCookie(cookie);
			
		}
		
		
//		System.out.println(jwtToken);
//		System.out.println(username);
//		
		if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
		
		  UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
		  Boolean validateToken = this.jwtHelper.isTokenValid(jwtToken, userDetails);
		  System.out.println("validity");
		  if (validateToken) {
			  
		  	//set the authentication
		  	SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		  	securityContext.setAuthentication(token);
		  	SecurityContextHolder.setContext(securityContext);
		  	
		     
		//              UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		//              token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		//              SecurityContextHolder.getContext().setAuthentication(token);
		
		
		  }


      }

      filterChain.doFilter(request, response);


  }

}
