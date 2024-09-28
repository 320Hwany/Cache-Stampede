package cache_stampede.dto;

public record ArticleHomeResponse(
        long articleId,
        String title,
        String author,
        int views
) {

    public static ArticleHomeResponse of(final ArticleOverviewResponse articleOverviewResponse,
                                         ArticleViewsResponse articleViewsResponse) {
        return new ArticleHomeResponse(
                articleOverviewResponse.articleId(),
                articleOverviewResponse.title(),
                articleOverviewResponse.author(),
                articleViewsResponse.views()
        );
    }
}
