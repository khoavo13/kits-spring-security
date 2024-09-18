package com.example.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("nai").password(passwordEncoder().encode("nai")).roles("USER").build()
        );
        manager.createUser(
                User.withUsername("bo").password(passwordEncoder().encode("bo")).roles("ADMIN").build()
        );
        return manager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/api/user/login").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/user/list/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic() // kich hoat xac thuc basic
                .and()
                .formLogin().disable();

        return http.build();
    }
}
