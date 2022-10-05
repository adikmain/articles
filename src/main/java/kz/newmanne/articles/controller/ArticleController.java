package kz.newmanne.articles.controller;


import kz.newmanne.articles.dto.ArticleDTO;
import kz.newmanne.articles.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/article")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody ArticleDTO articleDTO) {
        articleService.createArticle(articleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getArticles(@RequestParam int pageSize, @RequestParam int page) {
        if (pageSize <= 0 || pageSize > 50 || page < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(articleService.getArticles(pageSize, page), HttpStatus.OK);
    }
}
