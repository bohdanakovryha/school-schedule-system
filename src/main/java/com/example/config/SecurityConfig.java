package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
package com.example.config;

import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;*/
/*
@Configuration
*/
/*@EnableWebSecurity*//*

//@EnableOAuth2Client
@EnableMethodSecurity(jsr250Enabled = true)
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//@EnableAutoConfiguration(exclude = OAuth2ClientAutoConfiguration.class)
public class SecurityConfig {

    */
/*@Bean
    public KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setPrefix("ROLE_");
        mapper.setConvertToUpperCase(true);
        provider.setGrantedAuthoritiesMapper(mapper);
        return provider;
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }*//*


    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return authorities -> authorities.stream()
                .map(authority -> {
                    String role = authority.getAuthority();
                    if (role.startsWith("ROLE_")) {
                        return authority;
                    }
                    return new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());
                })
                .collect(Collectors.toSet());
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return request -> {
            OAuth2User user = delegate.loadUser(request);
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            Map<String, Object> realmAccess = user.getAttribute("realm_access");
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                List<String> roles = (List<String>) realmAccess.get("roles");
                for (String role : roles) {
                    mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
                }
            }

            return new DefaultOAuth2User(mappedAuthorities, user.getAttributes(), "preferred_username");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Статичні ресурси, login, публічні маршрути
                        .requestMatchers(
                                "/css/**", "/js/**", "/images/**",
                                "/login", "/access-denied"
                        ).permitAll()

                        // Доступ тільки для ADMIN
                        .requestMatchers(
                                "/admin/**",
                                "/adminPanel"
                        ).hasRole("ADMIN")

                        // Доступ для USER і ADMIN
                        .requestMatchers(
                                "/",
                                "/index",
                                "/classes",
                                "/teachers",
                                "/schedule/**",
                                "/api/classes/**",
                                "/api/teachers/**",
                                "/api/schedule/**"
                        ).hasAnyRole("USER", "ADMIN")

                        // Все інше — автентифіковано
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login") // має бути контролер
                        .defaultSuccessUrl("/index", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService())
                        )
                );

        return http.build();
    }


    */
/*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**",
                                "/js/**",
                                "/login",
                                "/login/start",
                                "/access-denied",
                                "/login/oauth2/code/**",
                                "/realms/school-scheduler/**",
                                "/.well-known/appspecific/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/", "/index", "/schedule").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("http://localhost:8080/login")
                        .defaultSuccessUrl("http://localhost:8080/index.html", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService())
                        )
                );

        return http.build();
    }*//*


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(
                ClientRegistration.withRegistrationId("keycloak")
                        .issuerUri("http://localhost:8081/realms/school-scheduler")
                        .clientId("spring-app")
                        .clientSecret("pnTNV9g52eB6kQswruM4OGufQjVItyMc")
                        .scope("openid", "profile", "email")
//                        .authorizationUri("http://keycloak-school-schedule:8080/realms/school-scheduler/protocol/openid-connect/auth")
                        .authorizationUri("http://keycloak-school-schedule:8080/login/oauth2/code/keycloak")
                        .tokenUri("http://keycloak-school-schedule:8080/realms/school-scheduler/protocol/openid-connect/token")
                        //.userInfoUri("http://localhost:8081/realms/school-scheduler/protocol/openid-connect/userinfo")
                        .userInfoUri("http://keycloak-school-schedule:8080/realms/school-scheduler/protocol/openid-connect/userinfo")

                        .jwkSetUri("http://localhost:8081/realms/school-scheduler/protocol/openid-connect/certs")
                        .userNameAttributeName("preferred_username")
                        .clientName("keycloak")
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                        .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                        .redirectUri("http://localhost:8080/login/oauth2/code/keycloak")
                        .build()
        );
    }
}
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setPrincipalClaimName("preferred_username");

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new JwtGrantedAuthoritiesConverter().convert(jwt);

            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            List<String> roles = realmAccess != null
                    ? (List<String>) realmAccess.get("roles")
                    : List.of();

            // Створюємо authorities із ролей без "ROLE_" префікса
            Stream<GrantedAuthority> roleAuthorities = roles.stream()
                    .map(SimpleGrantedAuthority::new);

            // Об’єднуємо базові authorities і рольові authorities (без "ROLE_")
            return Stream.concat(authorities.stream(), roleAuthorities).toList();
        });

        return converter;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/**").hasRole("USER") // або "ADMIN"
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/teachers/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/classes/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/schedule/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );
        return http.build();
    }
}
