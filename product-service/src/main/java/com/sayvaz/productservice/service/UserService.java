package com.sayvaz.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import com.sayvaz.productservice.entity.User;
import com.sayvaz.productservice.model.UserDTO;

@Service
public class UserService {
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void signup(UserDTO userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user = new User(userDto);
		jdbcUserDetailsManager.createUser(user);
	}

}
