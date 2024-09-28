package cache_stampede.application;

import cache_stampede.dto.ArticleCreateRequest;
import cache_stampede.dto.ArticleOverviewResponse;
import cache_stampede.persistence.Article;
import cache_stampede.persistence.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void createArticle(final ArticleCreateRequest dto) {
        Article article = Article.toEntity(dto.title(), dto.contents());
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleOverviewResponse> findAllOverviewWithoutCache() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
                .map(article -> ArticleOverviewResponse.of(article.getTitle(), article.getViews()))
                .collect(toList());
    }
}
