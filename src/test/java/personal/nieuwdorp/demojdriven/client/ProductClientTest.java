package personal.nieuwdorp.demojdriven.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import personal.nieuwdorp.demojdriven.client.domain.Product;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest
class ProductClientTest {
    private ProductClient productClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        wireMock.loadMappingsFrom("src/wiremock");
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.rootUri(wmRuntimeInfo.getHttpBaseUrl()).build();
        productClient = new ProductClient(restTemplate);
    }

    @Test
    void getAll() {
        Collection<Product> products = productClient.getAll();
        assertEquals(6, products.size());
    }
}