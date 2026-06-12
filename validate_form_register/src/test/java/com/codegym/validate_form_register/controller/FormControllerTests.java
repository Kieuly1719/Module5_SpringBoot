package com.codegym.validate_form_register.controller;

import com.codegym.validate_form_register.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
class FormControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.deleteAll();
    }

    @Test
    void showsRegistrationForm() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void rejectsInvalidRegistration() throws Exception {
        mockMvc.perform(post("/")
                        .param("firstName", "An")
                        .param("lastName", "")
                        .param("phoneNumber", "123")
                        .param("age", "17")
                        .param("email", "invalid"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors(
                        "user", "firstName", "lastName", "phoneNumber", "age", "email"));

        assertThat(userRepository.count()).isZero();
    }

    @Test
    void rejectsRegistrationWhenAgeIsBlank() throws Exception {
        mockMvc.perform(post("/")
                        .param("firstName", "Nguyen")
                        .param("lastName", "Van An")
                        .param("phoneNumber", "0912345678")
                        .param("email", "an@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors("user", "age"));

        assertThat(userRepository.count()).isZero();
    }

    @Test
    void savesValidRegistration() throws Exception {
        mockMvc.perform(post("/")
                        .param("firstName", "Nguyen")
                        .param("lastName", "Van An")
                        .param("phoneNumber", "0912345678")
                        .param("age", "20")
                        .param("email", "an@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("user"));

        assertThat(userRepository.count()).isEqualTo(1);
    }
}
