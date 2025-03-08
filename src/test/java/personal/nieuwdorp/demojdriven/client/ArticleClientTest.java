package personal.nieuwdorp.demojdriven.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import personal.nieuwdorp.demojdriven.client.domain.Article;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WireMockTest
class ArticleClientTest {

    private ArticleClient articleClient;

    @BeforeEach
    void setUp(WireMockRuntimeInfo wmRuntimeInfo) {
        WireMock wireMock = wmRuntimeInfo.getWireMock();
        wireMock.loadMappingsFrom("/home/vboxuser/IdeaProjects/demo-jdriven");
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.rootUri(wmRuntimeInfo.getHttpBaseUrl()).build();
        articleClient = new ArticleClient(restTemplate);
    }

    @Test
    void getSingle() {
        UUID randomUUID = UUID.randomUUID();
        Article article = articleClient.get(randomUUID);
        assertEquals(randomUUID, article.getUuid());
    }
}