package cache_stampede.dto;

public record ArticleViewsResponse(
        long articleId,
        int views
) {

    public static ArticleViewsResponse of(final long articleId, final int views) {
        return new ArticleViewsResponse(articleId, views);
    }
}
