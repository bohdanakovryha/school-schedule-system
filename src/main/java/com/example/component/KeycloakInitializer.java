/*
package com.example.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KeycloakInitializer implements CommandLineRunner {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master")
                .username("admin")
                .password("admin") // Адмін Keycloak
                .clientId("admin-cli")
                .build();

        // 1. Створити Realm, якщо ще немає
        if (keycloak.realms().findAll().stream().noneMatch(r -> r.getRealm().equals(realm))) {
            RealmRepresentation realmRep = new RealmRepresentation();
            realmRep.setRealm(realm);
            realmRep.setEnabled(true);
            keycloak.realms().create(realmRep);
        }

        RealmResource realmResource = keycloak.realm(realm);

        // 2. Створити клієнта, ролі та користувачів
        // ... (створення клієнта, ролей USER, ADMIN)
        // ... (зчитування users.json і створення юзерів з ролями)
    }
}
*/
