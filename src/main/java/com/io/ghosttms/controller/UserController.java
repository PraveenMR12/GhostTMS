package com.io.ghosttms.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.io.ghosttms.exceptionhandler.ResourceNotFoundException;
import com.io.ghosttms.security.JWTHelper;
import com.io.ghosttms.service.UserService;
import com.io.ghosttms.util.RequestObject;
import com.io.ghosttms.util.ResponseObject;
import com.io.ghosttms.util.UserDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController { 

	@Autowired
	UserService userService;
	@Autowired
	JWTHelper jwtHelper;
	
	@PostMapping("/registerUser")
	public String registerUser(@ModelAttribute UserDto userDto) {
		userService.registerUser(userDto);
		
		return"loginUser";
	}
	
	
	@PostMapping("/loginUser")
	public String loginUser(@ModelAttribute RequestObject requestObj, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		
		ResponseObject resObj = userService.loginUser(requestObj);
		System.out.println(resObj.getEmail());
		
		Cookie cookie = new Cookie("token", resObj.getToken());
		cookie.setMaxAge(9999);
		response.addCookie(cookie);
		
		
		UserDto user = userService.getUserByEmail(requestObj.getEmail());
		
		model.addAttribute("user", user);
//		session.setAttribute("user", user.getEmail());
		return "redirect:/user/home";
	}
	
	@GetMapping("/home")
	public String userHome(@ModelAttribute RequestObject requestObj, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		
		Cookie token = Arrays.asList(request.getCookies()).stream().filter(c->c.getName().equals("token")).findAny().orElseThrow(() -> new ResourceNotFoundException("Token", "", "Not Found"));
		String tokenString=token.getValue();
		
		String email = jwtHelper.extractUserName(tokenString);
		
		
		UserDto user = userService.getUserByEmail(email);
		
		model.addAttribute("user", user);
//		session.setAttribute("user", user.getEmail());
		return "UserHome";
	}
	
	
	@PostMapping("/home/updateUser")
	public String updateUser(@ModelAttribute UserDto user, HttpSession session, Model model) {
		String userEmail =	(String) session.getAttribute("user");
		user.setEmail(userEmail);
		UserDto savedUser = userService.updateUser(user);
		System.out.println(user.getFullName());
		
		model.addAttribute("user", savedUser);
		return "userHome"; 
		
	} 
	
	@GetMapping("/home/deleteUser")
	public String deleteUser(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {

		Cookie token = Arrays.asList(request.getCookies()).stream().filter(c->c.getName().equals("token")).findAny().orElseThrow(() -> new ResourceNotFoundException("Token", "", "Not Found"));
		String tokenString=token.getValue();
		
		String email = jwtHelper.extractUserName(tokenString);
		userService.deleteUser(email);
		token.setValue(null);
		token.setMaxAge(0);
		token.setPath("/user");
		response.addCookie(token);
		
		return "LoginUser"; 
		
	} 
	
	@GetMapping("/logout")
	public String logoutUser(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Logout");
		Cookie cookie = Arrays.asList(request.getCookies()).stream().filter(c -> c.getName().equals("token")).findAny().orElseThrow(() -> new ResourceNotFoundException("Token", "", "Not Found"));
		cookie.setValue(null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
//		session.setAttribute("User", "");
		return "Home";
	}
	
	@GetMapping("/home/allUsers")
	public String getAllUser(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		List<UserDto> userList = userService.getAllUser();
		
		model.addAttribute("users", userList);
		return "allUsers";
	}
	
	@PostMapping("/home/userByEmail")
	public String getUserByEmail(@RequestParam("email") String userEmail, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		UserDto user = userService.getUserByEmail(userEmail);
		System.out.println(user);
		model.addAttribute("users", user);
		
		return "allUsers";
	}
}
