/*
package com.example.schoolschedule.inregrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginPageLoads() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    */
/*@Test
    void loginWithValidCredentials() throws Exception {
        mockMvc.perform(formLogin("/login").user("admin@gmail.com").password("admin123"))
                .andExpect(authenticated());
    }
*//*

    @Test
    void loginWithInvalidCredentials() throws Exception {
        mockMvc.perform(formLogin("/login").user("admin@gmail.com").password("wrong"))
                .andExpect(unauthenticated());
    }

    @Test
    void loginWithMockOAuth2() throws Exception {
        mockMvc.perform(get("/some-protected-url").with(oauth2Login()))
                .andExpect(status().isOk())
                .andExpect(authenticated());
    }

    */
/*@Test
    void loginWithEmptyFields() throws Exception {
        mockMvc.perform(post("/login").param("username", "").param("password", ""))
                .andExpect(status().isBadRequest());
    }*//*

}

*/
