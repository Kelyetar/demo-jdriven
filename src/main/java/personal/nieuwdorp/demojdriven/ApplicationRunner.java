package personal.nieuwdorp.demojdriven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import personal.nieuwdorp.demojdriven.client.ArticleClient;
import personal.nieuwdorp.demojdriven.client.domain.Article;

import java.util.Collection;

@Service
@Slf4j
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
    private final ArticleClient articleClient;

    public ApplicationRunner(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    @Override
    public void run(ApplicationArguments args) {
        Collection<Article> articles = articleClient.getAll();
        articles.stream().map(Article::toString).forEach(log::info);
    }
}
