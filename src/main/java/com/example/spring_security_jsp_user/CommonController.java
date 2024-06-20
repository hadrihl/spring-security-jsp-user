package com.example.spring_security_jsp_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getHomepage() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String getSigninPage() {
		return "signin";
	}
	
	@GetMapping("/signup")
	public String getSignupPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String processSignup(@ModelAttribute("user") User user, Model model) {
		System.err.println("username: " + user.getUsername());
		if(userService.userExists(user.getUsername())) {
			model.addAttribute("errmsg", "User/Email already exists! Please try again.");
			return "signup";
		} else {
			userService.createNewUser(user);
			return "redirect:/";
		}
	}
}
