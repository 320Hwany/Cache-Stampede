package cache_stampede.article.application;

import cache_stampede.article.dto.ArticleCreateRequest;
import cache_stampede.article.persistence.Article;
import cache_stampede.article.persistence.ArticleRepository;
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
        Article article = Article.toEntity(dto.title(), dto.author(), dto.contents());
        articleRepository.save(article);
    }
}
