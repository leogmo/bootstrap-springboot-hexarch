package com.cjl.auth.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cjl.auth.infrastructure.security.jwt.JwtRequestFilter;
import com.cjl.auth.infrastructure.security.jwt.JwtTokenUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
        		"/hello", 
        		"/user/login",
        		"/user/signup",
        		"/user/password/recovery"        		
        	);
    }
	
	
}