package cache_stampede.dto;

public record ArticleBodyResponse(
        String title,
        String contents,
        int views
) {
}
