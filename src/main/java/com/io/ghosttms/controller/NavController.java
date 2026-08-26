package com.io.ghosttms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {
	
	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	@GetMapping("/user/Register")
	public String registerUser() {
		return "RegisterUser";
	}
	
	
	@GetMapping("/user/login")
	public String loginUser() {
		return "LoginUser";
	}
	
	@GetMapping("/user/updateUserPage")
	public String updateUser() {
		return "UpdateUser";
	}
	@GetMapping("/error")
	public String errorScreen(Model model, HttpSession session) {
		session.setAttribute("User", "");
		return "error";
	}
}
