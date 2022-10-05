package kz.newmanne.articles.controller;


import kz.newmanne.articles.dto.ArticleCountByDayDTO;
import kz.newmanne.articles.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
@Slf4j
public class AdminArticleController {
    private final ArticleService articleService;

    AdminArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article")
    public ResponseEntity<ArticleCountByDayDTO> getCount() {
        return new ResponseEntity<>(articleService.getCount(), HttpStatus.OK);
    }

}
