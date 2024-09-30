package cache_stampede.article.application;

import cache_stampede.article.vo.ArticleViews;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;


@Component
public class ArticleUpdater {

    private final ArticleFinder articleFinder;

    public ArticleUpdater(final ArticleFinder articleFinder) {
        this.articleFinder = articleFinder;
    }

    @CachePut(value = "ArticleFinder.findViewsById", key = "#articleId")
    public ArticleViews viewArticle(final long articleId) {
        ArticleViews articleViews = articleFinder.findViewsById(articleId);
        return articleViews.incrementViews();
    }
}
