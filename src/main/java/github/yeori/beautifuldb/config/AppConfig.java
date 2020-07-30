package github.yeori.beautifuldb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import github.yeori.dtogen.ObjectStamper;

@Configuration
public class AppConfig {

	@Bean
	public ObjectStamper objectPrinter() {
		return new ObjectStamper();
	}
}
