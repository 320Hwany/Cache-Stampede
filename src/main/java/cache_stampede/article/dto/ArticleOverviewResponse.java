package cache_stampede.article.dto;

public record ArticleOverviewResponse(
        long articleId,
        String title,
        String author
) {

    public static ArticleOverviewResponse of(final long articleId, final String title, final String author) {
        return new ArticleOverviewResponse(articleId, title, author);
    }
}
