package com.sayvaz.productservice.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class ProductPasswordEncoderFactories {
	
	private ProductPasswordEncoderFactories() {
	}
	
	public static PasswordEncoder createDelegatingPasswordEncoder() {
		String defaultEncoderId = "pbkdf2";
		PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", bcryptEncoder);
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(defaultEncoderId, encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(bcryptEncoder);
		
		return passwordEncoder;
	}

}
