package cache_stampede.article.presentation;

import cache_stampede.article.application.ArticleService;
import cache_stampede.article.dto.ArticleCreateRequest;
import cache_stampede.article.dto.ArticleHomeResponse;
import cache_stampede.global.dto.CollectionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public void createArticle(@RequestBody final ArticleCreateRequest dto) {
        articleService.createArticle(dto);
    }

    @GetMapping("/articles/{articleId}")
    public void viewArticle(@PathVariable final long articleId) {
        articleService.viewArticle(articleId);
    }

    @GetMapping("/articles-overview")
    public CollectionResponse<List<ArticleHomeResponse>> findAllOverview() {
        List<ArticleHomeResponse> articleHomeResponses = articleService.findAllOverview();
        return CollectionResponse.from(articleHomeResponses);
    }
}
