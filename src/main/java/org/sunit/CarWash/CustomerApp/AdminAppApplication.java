package org.sunit.CarWash.CustomerApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.sunit.CarWash"})
@EntityScan(basePackages = {"org.sunit.CarWash"})
@ComponentScan(basePackages = {"org.sunit.CarWash"})
public class AdminAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AdminAppApplication.class, args);
	}

}
