package github.yeori.beautifuldb.config.parts;

import org.springframework.security.crypto.password.PasswordEncoder;

import github.yeori.beautifuldb.dao.Enc;

public class Sha256Encoder implements PasswordEncoder {
	/*
	 * db에서 sha256으로 비번을 encoding 하고 있음
	 * 
	 * 아래는 비번 '1234'를 암호화하는 쿼리
	 * 
	 *   INSERT INTO users(email, `password`) VALUES ('test@test.com', SHA2('1234', 256));
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		return Enc.sha256(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}
	
}