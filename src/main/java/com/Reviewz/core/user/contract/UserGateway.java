package com.Reviewz.core.user.contract;

import java.util.List;
import java.util.Optional;

import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

public interface UserGateway {
	void create(UserSchema userSchema);
	List<UserSchema> findAll();
	void delete(UserSchema userSchema);
	UserSchema update(UserSchema userSchema);
	Optional<UserSchema> findOptionalByLogin(String login);
}
