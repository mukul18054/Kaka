//package com.work.kaka.config;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableWebSecurity // Might be needed depending on your Spring Security version
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().permitAll() // Allow all requests without authentication
//                .and()
//                .csrf().disable(); // Disable CSRF protection (adjust if needed)
//    }
//}
