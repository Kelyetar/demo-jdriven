package personal.nieuwdorp.demojdriven.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import personal.nieuwdorp.demojdriven.repository.domain.Article;

import java.util.UUID;

@Repository
public interface ArticleRepository extends CrudRepository<Article, UUID> {
}
