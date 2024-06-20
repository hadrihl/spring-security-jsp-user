package com.example.spring_security_jsp_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		System.err.println("username: " + user.getUsername());
		if(user == null) {
			throw new UsernameNotFoundException("User not found.");
			
		} else {
			return new CustomUserDetails(user);
		}
	}

}
