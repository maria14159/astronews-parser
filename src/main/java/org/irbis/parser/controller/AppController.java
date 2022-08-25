package org.irbis.parser.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppController {
    private final ArticleService articleService;
    private final ObjectMapper objectMapper;

    //127.0.0.1:8080/get_top_10
//    @GetMapping
    @RequestMapping(method = RequestMethod.GET, path = "/get_top_10")
    public List<Article> getTopTenArticles() {
        return articleService.getTopTenArticles();
    }

    @GetMapping("/get_all_by_author")
    public List<Article> getAllArticlesByAuthor(@RequestParam String name) {
        return articleService.getAllByAuthor(name);
    }

    @PostMapping("/add_author")
    public boolean addAuthor(@RequestBody Author author) {
        return articleService.addAuthor(author);
    }
}
