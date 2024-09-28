package cache_stampede.application;

import cache_stampede.dto.ArticleOverviewResponse;
import cache_stampede.persistence.Article;
import cache_stampede.persistence.ArticleRepository;
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
    public List<ArticleOverviewResponse> findAllOverviewWithoutCache() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(article -> ArticleOverviewResponse.of(article.getTitle(), article.getViews()))
                .collect(toList());
    }
}
