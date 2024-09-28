package cache_stampede.dto;


public record ArticleCreateRequest(
        String title,
        String author,
        String contents
) {
}
