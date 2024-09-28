package cache_stampede.application;

import cache_stampede.dto.ArticleCreateRequest;
import cache_stampede.dto.ArticleHomeResponse;
import cache_stampede.dto.ArticleOverviewResponse;
import cache_stampede.dto.ArticleViewsResponse;
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
            ArticleViewsResponse views = articleFinder.findViewsById(overviewResponse.articleId());

            articleHomeResponses.add(
                    ArticleHomeResponse.of(overviewResponse, views)
            );
        }

        return articleHomeResponses;
    }
}
