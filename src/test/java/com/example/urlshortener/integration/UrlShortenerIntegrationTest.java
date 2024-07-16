package com.example.urlshortener.integration;

import com.example.urlshortener.dto.UrlObject;
import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    private final String originalUrl = "https://google.com";
    private final String id = "d2f69089";

    @BeforeEach
    public void setUp() {
        urlRepository.deleteAll();
    }

    @Test
    public void testShortenUrl() throws Exception {
        UrlObject urlObject = new UrlObject();
        urlObject.setUrl(originalUrl);

        mockMvc.perform(post("/api/v1")
                        .content("{\"url\": \"" + originalUrl + "\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void testGetUrl() throws Exception {
        urlRepository.save(new UrlMapping(id, originalUrl));

        mockMvc.perform(get("/api/v1/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("Url Fetched Successfully"))
                .andExpect(jsonPath("$.url").value(originalUrl));
    }

    @Test
    public void testNullUrl() throws Exception {
        mockMvc.perform(post("/api/v1")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("Url Object cannot be null or empty"));
    }

    @Test
    public void testInvalidUrl() throws Exception {
        mockMvc.perform(post("/api/v1")
                        .content("{\"url\": \"http:\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("Invalid Url"));
    }
}
