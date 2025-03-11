package personal.nieuwdorp.demojdriven.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personal.nieuwdorp.demojdriven.client.domain.Article;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ArticleClient {
    private static final String ARTICLE = "article";
    private final RestTemplate restTemplate;

    public ArticleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Article getSingle(UUID uuid) {
        return restTemplate.getForObject("/%s/%s".formatted(ARTICLE, uuid.toString()), Article.class);
    }

    public Collection<Article> getAll() {
        return List.of(Objects.requireNonNull(restTemplate.getForObject("/%s".formatted(ARTICLE), Article[].class)));
    }
}
