package com.work.kaka.config;

import com.work.kaka.security.JwtAuthenticationEntryPoint;
import com.work.kaka.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/ws/**", "/topic/**", "/app/**").permitAll()
                .requestMatchers("/favicon.ico").permitAll()
                .requestMatchers("/chat.html").permitAll() // Allow access to chat.html
                .requestMatchers("/static/**").permitAll() // Allow access to all static resources
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/chat/**").permitAll()
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/requirements/**").permitAll()
                .requestMatchers("/api/communities").authenticated()
                .requestMatchers("/api/communities/{communityId}").authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}