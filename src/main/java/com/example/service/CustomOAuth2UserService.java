package com.example.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import java.util.Collections;
import java.util.Set;

public class CustomOAuth2UserService extends OidcUserService {

    private static final Set<String> ADMIN_EMAILS = Set.of("bogdanakovryha1@gmail.com");

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);

        String email = oidcUser.getEmail();

        if (!ADMIN_EMAILS.contains(email)) {
            throw new AccessDeniedException("Access denied for: " + email);
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

        return new DefaultOidcUser(Collections.singletonList(authority), oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}
