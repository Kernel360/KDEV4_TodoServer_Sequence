package com.example.jwt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtServiceTest {
	@Autowired
	private JwtService jwtService;

	@Test
	void createTest() {
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("user_id", "123");

		// 유효기간이 10초인 token
		LocalDateTime expireAt = LocalDateTime.now().plusSeconds(10);

		String jwtToken = jwtService.create(claims, expireAt);
		System.out.println(jwtToken);
	}

	@Test
	void validationTest() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMTIzIiwiZXhwIjoxNzQwNjIxODI3fQ.F8h6y__fzAbwe74jNDqeDTSJi5Fte4c7BmP9NkrNL0Y";
		jwtService.validation(token);
	}
}