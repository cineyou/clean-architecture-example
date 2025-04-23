package org.example;


import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CompteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_create_compte_courant_with_solde_1000() throws Exception {
        String requestBody = "{\"typeCompte\":\"COMPTE_COURANT\",\"montant\":1000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.solde").value(1000.0));


    }

    @Test
    public void should_create_compte_epargne_with_solde_1000() throws Exception {
        String requestBody = "{\"typeCompte\":\"COMPTE_EPARGNE\",\"montant\":1000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.solde").value(1000.0));
    }

    @Test
    public void should_not_create_compte_with_empty_type() throws Exception {
        String requestBody = "{\"typeCompte\":\"\",\"montant\":1000}";

         mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void should_not_create_compte_with_wrong_type() throws Exception {
        String requestBody = "{\"typeCompte\":\"TYPE_COMPTE_INEXISTANT\",\"montant\":2000}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comptes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void testGetAllComptes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/comptes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
