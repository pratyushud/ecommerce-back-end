package com.app.pd.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpClient;

@Configuration
public class AuthenticationConfig {

    @Bean
    public UserDetailsManager getInMemoryUsers(PasswordEncoder passwordEncoder) {
        UserDetails jack = User.builder()
                .username("jack")
                .password(passwordEncoder.encode("test123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(jack);
    }

    @Bean
    SecurityFilterChain authorizeUsers(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();
                })
                .csrf(csrf -> csrf.disable())
                .with(new HttpBasicConfigurer<>(), basic -> {});
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
