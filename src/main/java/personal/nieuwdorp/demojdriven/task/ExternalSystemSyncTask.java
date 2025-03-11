package personal.nieuwdorp.demojdriven.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import personal.nieuwdorp.demojdriven.client.ArticleClient;
import personal.nieuwdorp.demojdriven.client.domain.Article;
import personal.nieuwdorp.demojdriven.repository.ArticleRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
class ExternalSystemSyncTask implements Runnable {
    private final ArticleClient articleClient;
    private final ArticleRepository articleRepository;

    public ExternalSystemSyncTask(ArticleClient articleClient, ArticleRepository articleRepository) {
        this.articleClient = articleClient;
        this.articleRepository = articleRepository;
    }

    private static personal.nieuwdorp.demojdriven.repository.domain.Article createRepositoryArticle(Article a) {
        personal.nieuwdorp.demojdriven.repository.domain.Article article = new personal.nieuwdorp.demojdriven.repository.domain.Article();
        article.setUuid(a.getUuid());
        article.setPrice(a.getPrice());
        return article;
    }

    public void run() {
        Collection<Article> articles = articleClient.getAll();
        articleRepository.saveAll(articles.stream().map(ExternalSystemSyncTask::createRepositoryArticle).collect(Collectors.toSet()));
    }
}
