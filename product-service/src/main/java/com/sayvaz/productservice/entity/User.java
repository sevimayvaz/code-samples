package com.sayvaz.productservice.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sayvaz.productservice.model.UserDTO;

public class User implements UserDetails{
	
	
	private UserDTO userDto;
	
	public User(UserDTO userDto) {
		this.userDto = userDto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = null;
		if(userDto.getRoles()!= null && !userDto.getRoles().isEmpty()) {
			roles = userDto.getRoles().stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		}
		return roles;
	}

	@Override
	public String getPassword() {
		return userDto.getPassword();
	}

	@Override
	public String getUsername() {
		return userDto.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return userDto.isEnabled();
	}
	
	private static final long serialVersionUID = 3993305520928514405L;

}
