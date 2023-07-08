package com.Reviewz.core.user.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.Reviewz.core.user.exception.ValidationError;

public class UserTest {

	User user = new User();
	
	@Test
	public void setNameTest() throws Exception {
		user.setName("test name");
		assertEquals(user.getName(), "test name");
	}
	
	@Test
	public void setEmptyNameTest() {
		try {
			user.setName("");
		} catch (Exception e) {
			assertEquals(e.getClass(), ValidationError.class);
		}
	}
	
	@Test
	public void setEmailTest() throws Exception {
		user.setEmail("test@email.com");
		assertEquals(user.getEmail(), "test@email.com");
	}
	
	@Test
	public void setEmptyEmailTest() {
		try {
			user.setEmail("");
		} catch (Exception e) {
			assertEquals(e.getClass(), ValidationError.class);
		}
	}
	
	@Test
	public void setPasswordTest() throws Exception {
		user.setPassword("test password");
		assertEquals(user.getPassword(), "test password");
	}
	
	@Test
	public void setEmptyPasswordTest() {
		try {
			user.setPassword("");
		} catch (Exception e) {
			assertEquals(e.getClass(), ValidationError.class);
		}
	}
}