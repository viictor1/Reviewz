package com.Reviewz.dataprovider.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Reviewz.dataprovider.schema.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, Long>{
	Optional<UserSchema> findByEmail(String email);
}
