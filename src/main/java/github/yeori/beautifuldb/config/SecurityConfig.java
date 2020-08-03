package github.yeori.beautifuldb.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import github.yeori.beautifuldb.BeautDbException;
import github.yeori.beautifuldb.config.parts.JwtPasswordCheck;
import github.yeori.beautifuldb.config.parts.JwtTokenDecoingFilter;
import github.yeori.beautifuldb.service.token.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String defaultLoginPage = "/login";

	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	JwtService jwtService;
	
	@Value("${beautifuldb.cors.allowed-urls}")
	List<String> corsAllowedUrls;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/js/**", "/css/**", "/img/**", "/favicon.ico", "/error",
					"/oauth/**", "/loginByOAuth", "/join", "/ready")
				.anonymous()
				.anyRequest().authenticated()
			.and()
				.cors()
			.and()
				.csrf().csrfTokenRepository(tokenRepository())
			.and()
				.sessionManagement().disable()
//				.anonymous().disable()
//			.and()
			.formLogin()
					.disable()
//					.loginPage(defaultLoginPage)
//					.loginProcessingUrl("/doLogin")
//					.defaultSuccessUrl("/login/success")
//			.and()
				.exceptionHandling().authenticationEntryPoint(new UnAuthenticated(defaultLoginPage))
			.and()
				.addFilterBefore(jwtTokenDecoder(), UsernamePasswordAuthenticationFilter.class)
			;
	}
	
	@Bean CsrfTokenRepository tokenRepository() {
		return new CookieCsrfTokenRepository();
	}
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(corsAllowedUrls);
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
		provider.setPasswordEncoder(passwordVerifier());
		provider.setUserCache(userCache());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordVerifier() {
		// return new Sha256Encoder();
		return new JwtPasswordCheck();
	}
	
	@Bean
	public JwtTokenDecoingFilter jwtTokenDecoder() throws Exception {
		return new JwtTokenDecoingFilter(jwtService, authenticationManager());
	}
	@Bean
	public UserCache userCache() {
		return new SpringCacheBasedUserCache(new ConcurrentMapCache("authentication-cache"));
	}
	
	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailService;
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
//			String xhr = req.getHeader("X-Requested-With");
			Object exceptionValue = req.getAttribute("BEAUTIFULDB_APP_EXCEPTION");
			if (exceptionValue != null) {
				BeautDbException e = (BeautDbException) exceptionValue;
				res.setContentType("application/json; charset=UTF-8");
				String msg = e.asJson();
				res.getWriter().write(msg);
				res.setStatus(e.getResponseCode());
				res.setContentLength(msg.length());
				// res.sendError(e.getResponseCode());
				res.getWriter().flush();
			} else {
				// 
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
//			if ("XMLHttpRequest".equals(xhr)) {
//			} else {
//				super.commence(req, res, authException);
//			}
		}
		
	}
}
