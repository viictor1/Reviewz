package com.Reviewz.core.user.model;

import com.Reviewz.core.user.exception.ValidationError;

public class User {

	private Long id;
	private String name;
	private String email;
	private String password;
	
	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password) throws Exception {
		super();
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		if(name == null || name.isEmpty()) {
			throw new ValidationError("The name cannot be blank or empty");
		}
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if(email == null || email.isEmpty()) {
			throw new ValidationError("The email cannot be blank or empty");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		if(password == null || password.isEmpty()) {
			throw new ValidationError("The password cannot be blank or empty");
		}
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
