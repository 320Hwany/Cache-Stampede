package cache_stampede.article.application;

import cache_stampede.article.dto.ArticleCreateRequest;
import cache_stampede.article.dto.ArticleHomeResponse;
import cache_stampede.article.dto.ArticleOverviewResponse;
import cache_stampede.article.vo.ArticleViews;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleService {

    private final ArticleAppender articleAppender;
    private final ArticleFinder articleFinder;

    public ArticleService(final ArticleAppender articleAppender, final ArticleFinder articleFinder) {
        this.articleAppender = articleAppender;
        this.articleFinder = articleFinder;
    }

    @Transactional
    public void createArticle(final ArticleCreateRequest dto) {
        articleAppender.createArticle(dto);
    }

    @Transactional(readOnly = true)
    public List<ArticleHomeResponse> findAllOverview() {
        List<ArticleOverviewResponse> overviewResponses = articleFinder.findAllOverview();
        List<ArticleHomeResponse> articleHomeResponses = new ArrayList<>();

        for (ArticleOverviewResponse overviewResponse : overviewResponses) {
            ArticleViews views = articleFinder.findViewsById(overviewResponse.articleId());

            articleHomeResponses.add(
                    ArticleHomeResponse.of(overviewResponse, views)
            );
        }

        return articleHomeResponses;
    }

    @CachePut(value = "ArticleFinder.findViewsById", key = "#articleId")
    public ArticleViews viewArticle(final long articleId) {
        ArticleViews articleViews = articleFinder.findViewsById(articleId);
        return articleViews.incrementViews();
    }
}
