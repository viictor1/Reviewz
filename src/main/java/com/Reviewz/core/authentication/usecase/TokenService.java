package com.Reviewz.core.authentication.usecase;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Reviewz.dataprovider.schema.UserSchema;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(UserSchema userSchema) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT
					.create()
					.withIssuer("Reviewz")
					.withSubject(userSchema.getLogin())
					.withExpiresAt(generateExpirationDate())
					.sign(algorithm);
			return token;
		}
		catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token");
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT
					.require(algorithm)
					.withIssuer("Reviewz")
					.build()
					.verify(token)
					.getSubject();
		}
		catch (JWTVerificationException e) { 
			return ""; //Retornando uma string vazia, ao autenticar ele já retornará um unauthorized
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
