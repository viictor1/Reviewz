package com.Reviewz.infrastructure.config.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Reviewz.infrastructure.config.db.schema.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, Long>{
	Optional<UserSchema> findByEmail(String email);
}
