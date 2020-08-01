package github.yeori.beautifuldb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import github.yeori.dtommic.DtoMimic;

@Configuration
public class AppConfig {

	@Bean
	public DtoMimic dtoMimic() {
		return new DtoMimic();
	}
}
