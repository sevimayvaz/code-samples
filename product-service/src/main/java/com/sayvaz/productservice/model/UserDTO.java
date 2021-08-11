package com.sayvaz.productservice.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO implements Serializable{
	
	private String username;
	private String password;
	private boolean enabled;
	private List<String> roles; 

	private static final long serialVersionUID = -1278472945899030229L;
}
