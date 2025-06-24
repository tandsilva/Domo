package com.exozonia.domo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Desativa CSRF (opcional pra Swagger)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/swagger-ui.html",
//                                "/v3/api-docs/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(httpBasic -> {}); // forma moderna de ativar HTTP Basic
//
//        return http.build();
//    }
//}
