package com.turnos.turnosSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class TurnosConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Públicas
                        .requestMatchers(HttpMethod.POST, "/api/turnos").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/turnos/cola").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/sectores").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/campos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/google").permitAll()

                        // Admin
                        .requestMatchers("/api/turnos/*/sector").authenticated()
                        .requestMatchers("/api/turnos/*/estado").authenticated()
                        .requestMatchers("/api/sectores/**").authenticated()
                        .requestMatchers("/api/campos/**").authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}