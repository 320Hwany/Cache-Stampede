package cache_stampede.dto;

public record ArticleOverviewResponse(
        String title,
        int views
) {

    public static ArticleOverviewResponse of(final String title, final int views) {
        return new ArticleOverviewResponse(title, views);
    }
}
