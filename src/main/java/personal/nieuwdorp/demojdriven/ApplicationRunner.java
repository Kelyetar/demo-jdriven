package personal.nieuwdorp.demojdriven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;
import personal.nieuwdorp.demojdriven.client.ArticleClient;

import java.util.UUID;

@Service
@Slf4j
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
    private final ArticleClient articleClient;

    public ApplicationRunner(ArticleClient articleClient) {
        this.articleClient = articleClient;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info(articleClient.getSingle(UUID.randomUUID()).toString());
    }
}
