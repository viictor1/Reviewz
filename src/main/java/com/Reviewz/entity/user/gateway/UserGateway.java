package com.Reviewz.entity.user.gateway;

import java.util.Optional;

import com.Reviewz.entity.user.model.User;

public interface UserGateway {
	void create(User user);
	
	Optional<User> findByEmail(String email);
}
