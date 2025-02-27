package com.example.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {
	private static String secret = "javaspringboot11jwttokencreateandvalidation";

	/**
	 * token 생성
	 * @param claims
	 * @param expireAt
	 * @return
	 */
	public String create(
			Map<String, Object> claims,
			LocalDateTime expireAt
	) {
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
		Date _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

		return Jwts.builder()
				.signWith(key)
				.setClaims(claims)
				.setExpiration(_expireAt)
				.compact();
	}

	/**
	 * token 검증
	 * @param token
	 */
	public void validation(String token) {
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

		JwtParser parser = Jwts.parserBuilder()
				.setSigningKey(key)
				.build();

		try {
			Jws<Claims> result = parser.parseClaimsJws(token);

			result.getBody().forEach((k, v) -> log.info("key: {}, value: {}", k, v));

		} catch (SignatureException e) {
			throw new RuntimeException("JWT Token Not Valid Exception");
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("JWT Token Expired Exception");
		}
	}
}
