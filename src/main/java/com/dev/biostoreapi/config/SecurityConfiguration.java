package com.dev.biostoreapi.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
////        return httpSecurity
//                .exceptionHandling().authenticationEntryPoint()
//                .and()
//                .addFilterBefore(new JwtAuthFilter(), BasicAuthenticationFilter.class)
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(HttpMethod.POST)).build();

        return   httpSecurity.authorizeHttpRequests(
                    authorizeRequests ->authorizeRequests
                        .requestMatchers("/api/v1", "/api/v1/users/login", "/api/v1/users/register", "/api/v1/categories/*").permitAll()
                        .anyRequest().authenticated()

        ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
