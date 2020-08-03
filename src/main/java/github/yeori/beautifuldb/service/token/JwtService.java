package github.yeori.beautifuldb.service.token;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.TypeMap;
import github.yeori.beautifuldb.Util;
import github.yeori.beautifuldb.model.user.User;
import github.yeori.beautifuldb.Error;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {

	private static Logger logger = LoggerFactory.getLogger(JwtService.class);
	@Value("${beautifuldb.jwt.secret}") private String secretKey;
	
	private byte [] keyBytes;
	
	public JwtService() {
		
	}
	public String createToken(User user) {
		if (keyBytes == null) {
			// System.out.println("[SECRET] " + secretKey);
			logger.info("[JWT SECRET] INIT");
			keyBytes = secretKey.getBytes();
		}
		Date time = Util.during("1 day");
		return Jwts.builder()
			.setHeaderParam("type", "JWT")
			.setSubject(user.getLoginAccount().getEmail())
			.claim("oauthId", user.getLoginAccount().getId())
			.setExpiration(time)
			.signWith(SignatureAlgorithm.HS256, keyBytes)
			.compact();
	}
	
	public TypeMap decodeToken(String jwtToken) throws BeautDbException {
		if (keyBytes == null) {
			logger.info("[JWT SECRET] INIT");
			keyBytes = secretKey.getBytes();
		}
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(keyBytes).parseClaimsJws(jwtToken);
			System.out.println(claims);
			TypeMap body = new TypeMap(claims.getBody());
			return body;			
		} catch (JwtException e) {
			String errorType = e.getClass().getName();
			throw new BeautDbException(
				401,
				Error.INVALID_AUTH_KEY.name(),
				"key problem [%s]", Util.camelToSnake(errorType).toUpperCase());
		}
	}

}
