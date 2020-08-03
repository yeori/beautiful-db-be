package github.yeori.beautifuldb.service.token;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.Util;
import github.yeori.beautifuldb.model.user.OAuthAccount;
import github.yeori.beautifuldb.model.user.User;

@SpringBootTest
class JwtServiceTest {

	@Autowired
	JwtService jwtService;
	
	@Test
	void test_jwt_토큰생성() {
		User user = new User("google 104489317518647324149", null);
		OAuthAccount acc = new OAuthAccount();
		acc.setEmail("chmin.seo@gmail.com");
		acc.setId("104489317518647324149");
		user.setLoginAccount(acc);
		
		System.out.println(jwtService.createToken(user));
	}
	
	@Test
	public void test_jwt_토큰디코딩() {
		/*
		1596371537502
eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJjaG1pbi5zZW9AZ21haWwuY29tIiwib2F1dGhJZCI6IjEwNDQ4OTMxNzUxODY0NzMyNDE0OSIsImV4cCI6MTU5NjM3MTUzN30.d0t5uw9ihYrP-xxrM1eWCEsgW5MxmnWTRRf6XI73BU4

		 */
		String jwt = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJjaG1pbi5zZW9AZ21haWwuY29tIiwib2F1dGhJZCI6IjEwNDQ4OTMxNzUxODY0NzMyNDE0OSIsImV4cCI6MTU5NjM3MTUzN30.d0t5uw9ihYrP-xxrM1eWCEsgW5MxmnWTRRf6XI73BU4";
		TypeMap body = jwtService.decodeToken(jwt);
		assertEquals("chmin.seo@gmail.com", body.get("sub"));
		assertEquals(1596371537502L/1000, body.asLong("exp"));
	}
	
	@Test
	public void test_1일후() {
		Date d = Util.during("1 day");
		System.out.println(d.toString());
		System.out.println(d.getTime());
	}

}
