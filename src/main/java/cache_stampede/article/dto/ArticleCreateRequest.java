package cache_stampede.article.dto;


public record ArticleCreateRequest(
        String title,
        String author,
        String contents
) {
}
