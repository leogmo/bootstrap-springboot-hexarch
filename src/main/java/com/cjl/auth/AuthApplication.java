package com.cjl.auth;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cjl.auth.adapters.out.jpa.AccountJpaRepositoryAdapter;
import com.cjl.auth.application.usecase.account.LoginUseCase;
import com.cjl.auth.infraestructure.security.JwtTokenUtil;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.cjl.auth")
@EnableAutoConfiguration
public class AuthApplication {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	AccountJpaRepositoryAdapter accountJpaRepositoryAdapter;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(Arrays.asList("*"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    config.setAllowCredentials(true);
	    config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

	    final UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
	    configSource.registerCorsConfiguration("/**", config);

	    return configSource;
	}
}
