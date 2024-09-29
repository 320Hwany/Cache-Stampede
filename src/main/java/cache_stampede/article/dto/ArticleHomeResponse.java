package cache_stampede.article.dto;

import cache_stampede.article.vo.ArticleViews;

public record ArticleHomeResponse(
        long articleId,
        String title,
        String author,
        int views
) {

    public static ArticleHomeResponse of(final ArticleOverviewResponse articleOverviewResponse,
                                         ArticleViews articleViews) {
        return new ArticleHomeResponse(
                articleOverviewResponse.articleId(),
                articleOverviewResponse.title(),
                articleOverviewResponse.author(),
                articleViews.views()
        );
    }
}
