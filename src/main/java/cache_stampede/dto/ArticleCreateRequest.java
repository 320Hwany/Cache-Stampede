package cache_stampede.dto;

import cache_stampede.persistence.Article;

public record ArticleCreateRequest(
        String title,
        String contents
) {
}
