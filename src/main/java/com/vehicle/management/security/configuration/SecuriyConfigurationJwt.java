package com.vehicle.management.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vehicle.management.security.JwtAuthenticationEntryPoint;
import com.vehicle.management.security.JwtAuthenticationFilter;

@Configuration
public class SecuriyConfigurationJwt {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	http.csrf(csrf->csrf.disable())
    	.authorizeHttpRequests(auth->auth
    			.requestMatchers(  "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html").permitAll()
    			.requestMatchers("/api/login").permitAll().anyRequest().authenticated())
    	.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) ;
    	
    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }

}
