package cache_stampede.article.application;

import cache_stampede.article.dto.ArticleCreateRequest;
import cache_stampede.article.dto.ArticleHomeResponse;
import cache_stampede.article.dto.ArticleOverviewResponse;
import cache_stampede.article.vo.ArticleViews;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class ArticleService {

    private final ArticleAppender articleAppender;
    private final ArticleFinder articleFinder;
    private final ArticleUpdater articleUpdater;

    private final Map<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public ArticleService(final ArticleAppender articleAppender, final ArticleFinder articleFinder,
                          final ArticleUpdater articleUpdater) {
        this.articleAppender = articleAppender;
        this.articleFinder = articleFinder;
        this.articleUpdater = articleUpdater;
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

    public void viewArticle(final long articleId) {
        ReentrantLock lock = lockMap.computeIfAbsent(articleId, key -> new ReentrantLock());
        lock.lock();

        try {
            articleUpdater.viewArticle(articleId);
        } finally {
            lock.unlock();
        }
    }
}
