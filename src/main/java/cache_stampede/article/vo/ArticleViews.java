package cache_stampede.article.vo;

public record ArticleViews(
        long articleId,
        int views
) {

    public static ArticleViews of(final long articleId, final int views) {
        return new ArticleViews(articleId, views);
    }

    public ArticleViews incrementViews() {
        return new ArticleViews(articleId, views + 1);
    }
}
