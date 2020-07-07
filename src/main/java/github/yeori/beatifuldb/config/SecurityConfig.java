package github.yeori.beatifuldb.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
}
