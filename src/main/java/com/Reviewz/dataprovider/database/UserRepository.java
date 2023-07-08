package com.Reviewz.dataprovider.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.Reviewz.dataprovider.schema.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, String>{
	
	@Query(value = "SELECT u FROM UserSchema u WHERE u.email = :email")
	Optional<UserSchema> findOptionalByEmail(@Param("email") String email);
	
	UserDetails findByEmail(String email);
}
