package com.example.urlshortener.service;


import com.example.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UrlShorteningServiceTest {

    @Autowired
    UrlShorteningService service;

    @MockBean
    UrlRepository repository;

    @Test
    void testShortenUrl() {
        String originalUrl = "https://www.google.com/search?q=how+to+get+the+root+url+of+a+get+call+in+java&oq=how+to+get+the+root+url+of+a+get+call+in+java&gs_lcrp=EgZjaHJvbWUyCQgAEEUYORigATIHCAEQIRigATIHCAIQIRigATIHCAMQIRigATIHCAQQIRigAdIBCTEwMDk1ajBqN6gCALACAA&sourceid=chrome&ie=UTF-8";
        String id = "d2f69089";
        doNothing().when(repository).setID(id, originalUrl);
        Assertions.assertEquals(id, service.getShortenedId(originalUrl));
    }

    @Test
    void testLongUrl(){
        String originalUrl = "https://www.google.com/search?q=how+to+get+the+root+url+of+a+get+call+in+java&oq=how+to+get+the+root+url+of+a+get+call+in+java&gs_lcrp=EgZjaHJvbWUyCQgAEEUYORigATIHCAEQIRigATIHCAIQIRigATIHCAMQIRigATIHCAQQIRigAdIBCTEwMDk1ajBqN6gCALACAA&sourceid=chrome&ie=UTF-8";
        String id = "d2f69089";
        when(repository.fetchUrl(id)).thenReturn(originalUrl);
        Assertions.assertEquals(originalUrl, service.getLongUrl(id));
    }
}
