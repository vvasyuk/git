package com.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAppTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnHello() throws Exception {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("http://localhost:8080/hi", String.class);
        then(entity.getBody()).isEqualTo("hello");
    }
}
