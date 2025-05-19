/*
package com.example.security;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PublicKey;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

@Component
public class KeycloakJwtAuthenticationFilter extends OncePerRequestFilter {

    private final PublicKey keycloakPublicKey;

    public KeycloakJwtAuthenticationFilter(PublicKey keycloakPublicKey) {
        this.keycloakPublicKey = keycloakPublicKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String tokenString = authHeader.substring(7);
            try {
                SignedJWT signedJWT = SignedJWT.parse(tokenString);

                // Перевірка підпису
                if (signedJWT.verify(new RSASSAVerifier((java.security.interfaces.RSAPublicKey) keycloakPublicKey))) {
                    JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

                    String username = claims.getStringClaim("preferred_username");
                    List<String> roles = claims.getJSONObjectClaim("realm_access") != null
                            ? (List<String>) ((List<?>) ((java.util.Map<?, ?>) claims.getJSONObjectClaim("realm_access")).get("roles"))
                            : Collections.emptyList();

                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(username, null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
*/
