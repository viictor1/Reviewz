package com.Reviewz.dataprovider.database;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.Reviewz.dataprovider.schema.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, UUID>{
	
	@Query(value = "SELECT u FROM UserSchema u WHERE u.login = :login")
	Optional<UserSchema> findOptionalByLogin(@Param("login") String login);
	
	UserDetails findByLogin(String login);
}
