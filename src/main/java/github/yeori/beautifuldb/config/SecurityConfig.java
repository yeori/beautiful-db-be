package github.yeori.beautifuldb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import github.yeori.beautifuldb.dao.Enc;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http.authorizeRequests()
			.antMatchers("/", "/js/**", "/css/**", "/img/**")
				.permitAll()
			.anyRequest()
				.authenticated().and()
				.formLogin().and()
				.httpBasic();

	}
	
	@Bean
	AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(sha256Encoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder sha256Encoder() {
		return new Sha256Encoder();
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailService;
	}
	
	static class Sha256Encoder implements PasswordEncoder {
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
}
