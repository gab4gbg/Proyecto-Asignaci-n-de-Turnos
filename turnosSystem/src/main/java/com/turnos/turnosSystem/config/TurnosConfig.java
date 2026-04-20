package com.turnos.turnosSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class TurnosConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitado por ahora — en producción se habilita con tokens
                .authorizeHttpRequests(auth -> auth

                        // Rutas públicas (cualquier usuario sin login)
                        .requestMatchers(HttpMethod.POST, "/api/turnos").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/api/turnos/cola").permitAll()

                        // Rutas de admin (requieren autenticación)
                        .requestMatchers("/api/turnos/*/sector").authenticated()
                        .requestMatchers("/api/turnos/*/estado").authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {}); // Autenticación básica por ahora

        return http.build();
    }
}