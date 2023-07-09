package com.Reviewz.core.user.contract;

import java.util.Optional;

import com.Reviewz.core.user.model.User;

public interface UserGateway {
	void create(User user);

	Optional<User> findOptionalByLogin(String login);
}
