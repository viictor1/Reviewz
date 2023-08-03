package com.Reviewz.core.user.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

public class User {

	private UUID id;
	private String name;
	private String login;
	private String password;
	private UserRole role;

	private Set<ReviewSchema> reviews = new HashSet<>();
	
	public User(UUID id, String name, String login, String password, UserRole role) {
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

	public User(UserSchema user) {
		this.id = user.getId();
		this.name = user.getName();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.role = user.getRole();
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
			throw new ValidationError("The login cannot be blank or empty");
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

	public UUID getId() {
		return id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

    public void setId(UUID uuid) {
		this.id = uuid;
    }

	public Set<ReviewSchema> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewSchema> reviews) {
		this.reviews = reviews;
	}
}
