package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlObject;
import com.example.urlshortener.service.UrlShorteningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShorteningService urlShorteningService;

    private final String originalUrl = "https://www.google.com";
    private final String id = "d2f69089";

    @Test
    public void testShortenUrlController() throws Exception {
        when(urlShorteningService.getShortenedId(originalUrl)).thenReturn(id);
        UrlObject urlObject = new UrlObject();
        urlObject.setUrl(originalUrl);
        when(urlShorteningService.validateUrl(urlObject)).thenReturn(null);

        mockMvc.perform(post("/api/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://www.google.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("Url Fetched Successfully"))
                .andExpect(jsonPath("$.url").value("http://localhost/api/v1/"+id));

        verify(urlShorteningService, times(1)).getShortenedId(originalUrl);
    }

    @Test
    public void testGetUrlController() throws Exception {
        when(urlShorteningService.getLongUrl(id)).thenReturn(originalUrl);

        mockMvc.perform(get("/api/v1/" + id).content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("Url Fetched Successfully"))
                .andExpect(jsonPath("$.url").value(originalUrl));

        verify(urlShorteningService, times(1)).getLongUrl(id);
    }
}
