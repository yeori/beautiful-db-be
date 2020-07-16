package github.yeori.beautifuldb.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import github.yeori.beautifuldb.dao.Enc;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String defaultLoginPage = "/login";

	@Autowired
	UserDetailsService userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http
			.authorizeRequests()
//			.antMatchers("/**").permitAll()
//			.and()
//			.authorizeRequests()
			.antMatchers("/", "/js/**", "/css/**", "/img/**", "/favicon.ico",  
					"/login", "/oauth/**", "/member", "/join")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
				.cors()
			.and()
				.formLogin()
					.loginPage(defaultLoginPage)
					.loginProcessingUrl("/doLogin")
					.defaultSuccessUrl("/login/success")
			.and()
				.exceptionHandling().authenticationEntryPoint(new UnAuthenticated(defaultLoginPage))
			.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://dev.beautifuldb.kr"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
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
	
	// static class UnAuthenticated implements AuthenticationEntryPoint {
	 static class UnAuthenticated extends LoginUrlAuthenticationEntryPoint {

		public UnAuthenticated(String loginFormUrl) {
			super(loginFormUrl);
		}

		@Override
		public void commence(
				HttpServletRequest req,
				HttpServletResponse res,
				AuthenticationException authException) throws IOException, ServletException {
			// throw 401 response
			String xhr = req.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(xhr)) {
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);	
			} else {
				super.commence(req, res, authException);
			}
		}
		
	}
}
