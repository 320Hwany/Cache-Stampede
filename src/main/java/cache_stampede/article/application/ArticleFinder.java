package cache_stampede.article.application;

import cache_stampede.article.dto.ArticleOverviewResponse;
import cache_stampede.article.vo.ArticleViews;
import cache_stampede.article.persistence.Article;
import cache_stampede.article.persistence.ArticleRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ArticleFinder {

    private final ArticleRepository articleRepository;

    public ArticleFinder(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional(readOnly = true)
    public List<ArticleOverviewResponse> findAllOverview() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(article ->
                        ArticleOverviewResponse.of(
                                article.getId(),
                                article.getTitle(),
                                article.getAuthor()
                        )
                )
                .collect(toList());
    }

    @Cacheable(value = "ArticleFinder.findViewsById", key = "#articleId")
    @Transactional(readOnly = true)
    public ArticleViews findViewsById(final long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(IllegalArgumentException::new);
        return ArticleViews.of(articleId, article.getViews());
    }
}
