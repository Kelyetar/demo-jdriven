package personal.nieuwdorp.demojdriven.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal.nieuwdorp.demojdriven.client.domain.Article;

import java.util.UUID;

@Service
public class ArticleClient {
    public static final String ARTICLE = "article";
    private final RestTemplate restTemplate;

    public ArticleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Article get(UUID uuid) {
        return restTemplate.getForObject("/%s/%s".formatted(ARTICLE, uuid.toString()), Article.class);
    }
}
