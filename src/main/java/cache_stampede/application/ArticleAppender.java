package cache_stampede.application;

import cache_stampede.dto.ArticleCreateRequest;
import cache_stampede.persistence.Article;
import cache_stampede.persistence.ArticleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ArticleAppender {

    private final ArticleRepository articleRepository;

    public ArticleAppender(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void createArticle(final ArticleCreateRequest dto) {
        Article article = Article.toEntity(dto.title(), dto.contents());
        articleRepository.save(article);
    }
}
