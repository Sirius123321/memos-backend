package org.lgz.memosbackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureWebTestClient
class MemosBackendApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    public void accessSecureEndpointWithBasicAuth() throws Exception {
        webTestClient.get().uri("/memo")
                .headers(headers -> headers.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello");
    }
}
