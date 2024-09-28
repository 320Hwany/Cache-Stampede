package cache_stampede.presentation;

import cache_stampede.application.ArticleService;
import cache_stampede.dto.*;
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
    public CollectionResponse<List<ArticleHomeResponse>> findAllOverview() {
        List<ArticleHomeResponse> articleHomeResponses = articleService.findAllOverview();
        return CollectionResponse.from(articleHomeResponses);
    }
}
