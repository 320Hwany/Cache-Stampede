package cache_stampede.presentation;

import cache_stampede.application.ArticleService;
import cache_stampede.dto.ArticleCreateRequest;
import cache_stampede.dto.ArticleOverviewResponse;
import cache_stampede.dto.CollectionResponse;
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

    @GetMapping("/articles-overview")
    public CollectionResponse<List<ArticleOverviewResponse>> findAllOverview() {
        List<ArticleOverviewResponse> articleOverviewResponses = articleService.findAllOverview();
        return CollectionResponse.from(articleOverviewResponses);
    }

    @GetMapping("/articles-overview-with-cache")
    public CollectionResponse<List<ArticleOverviewResponse>> findAllOverviewWithCache() {
        List<ArticleOverviewResponse> articleOverviewResponses = articleService.findAllOverviewWithCache();
        return CollectionResponse.from(articleOverviewResponses);
    }
}
