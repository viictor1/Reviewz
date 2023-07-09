package com.Reviewz.core.user.model;

import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.dataprovider.schema.UserRole;

public class User {

	private String id;
	private String name;
	private String login;
	private String password;
	private UserRole role;
	
	public User(String id, String name, String login, String password, UserRole role) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.setRole(role);
	}

	public User(String name, String login, String password) throws Exception {
		super();
		this.setName(name);
		this.setLogin(login);
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws Exception {
		if(login == null || login.isEmpty()) {
			throw new ValidationError("The email cannot be blank or empty");
		}
		this.login = login;
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

	public String getId() {
		return id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
	
}
