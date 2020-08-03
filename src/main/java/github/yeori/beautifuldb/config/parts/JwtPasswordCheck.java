package github.yeori.beautifuldb.config.parts;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * jwt 토큰을 열어서 안에 담긴 email로 사용자를 인증하기때문에 비번을 확인하지 않음
 * @author yeori
 */
public class JwtPasswordCheck implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return true;
	}
}