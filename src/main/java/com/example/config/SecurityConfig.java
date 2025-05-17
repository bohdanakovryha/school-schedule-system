package com.example.config;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationEntryPoint;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/adminPanel").hasRole("ADMIN")
                        .requestMatchers("/", "/index.html").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak") // Явно вказати сторінку логіну
                        .defaultSuccessUrl("/index.html", true)       // Редірект після успішного логіну
                        .failureUrl("/login?error")                    // На випадок помилки
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("http://localhost:8081/realms/school-scheduler/protocol/openid-connect/logout?redirect_uri=http://localhost:8080/")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
