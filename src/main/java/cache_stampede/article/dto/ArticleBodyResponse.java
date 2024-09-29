package cache_stampede.article.dto;

public record ArticleBodyResponse(
        String title,
        String contents,
        int views
) {
}
