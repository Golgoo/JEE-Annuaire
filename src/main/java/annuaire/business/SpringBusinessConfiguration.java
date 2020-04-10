package annuaire.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import annuaire.web.User;

@Configuration
@ComponentScan(basePackageClasses = SpringBusinessConfiguration.class)
public class SpringBusinessConfiguration {
	@Bean
	public User createUser() {
		System.out.println("Session user created");
		return new User();
	}
}
