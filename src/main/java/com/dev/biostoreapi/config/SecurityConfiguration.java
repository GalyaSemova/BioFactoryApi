package com.dev.biostoreapi.config;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

//        return   httpSecurity.authorizeHttpRequests(
//                    authorizeRequests ->authorizeRequests
//                        .requestMatchers("/api/v1"
//                                , "/api/v1/users/login", "/api/v1/users/register"
//                                ,"/api/v1/categories", "/api/v1/categories/*").permitAll()
//                            .requestMatchers("api/v1/products/all").permitAll()
//                        .anyRequest().authenticated()
//
//        ).build();

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1"
                                , "/api/v1/users/login", "/api/v1/users/register"
                                ,"/api/v1/categories", "/api/v1/categories/*").permitAll()
                            .requestMatchers("api/v1/products/all").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return httpSecurity.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
