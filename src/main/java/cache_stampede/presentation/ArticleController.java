package cache_stampede.presentation;

import cache_stampede.application.ArticleService;
import cache_stampede.dto.ArticleCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
