package org.irbis.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.irbis.parser.model.Article;
import org.irbis.parser.model.Comment;
import org.irbis.parser.repository.ArticleRepository;
import org.irbis.parser.service.SiteLoader;
import org.irbis.parser.service.SiteParser;
import org.irbis.parser.service.Storage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class LearnJavaParserApplication implements CommandLineRunner {

    private final SiteLoader loader;
    private final SiteParser parser;
    private final Storage storage;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        SpringApplication.run(LearnJavaParserApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        String siteUrl = "https://www.astronews.ru";
        String response = loader.load(siteUrl);

        HashMap<Article, List<Comment>> articles = new HashMap<>();

        List<String> articleLinks = parser.parseMain(response, siteUrl);

//        List<Runnable> tasks = new ArrayList<>();
//        for (String url : articleLinks) {
//            tasks.add(() -> {
//                log.info("start work with {}", url);
//                String load = loader.load(url);
//                Article article = parser.parseArticle(load);
//                List<Comment> comments = parser.parseComments(load, article);
//                storage.save(article, comments);
////                articles.put(article, comments);
//                log.info("save from {} done", url);
//            });
//        }
//
//        tasks.forEach(executorService::submit);
//
//        executorService.shutdown();


        for (String articleLink: articleLinks) {
            String response1 = loader.load(articleLink);
            Article article = parser.parseArticle(response1);
            List<Comment> comments = parser.parseComments(response1, article);
            articles.put(article, comments);
        }

        storage.save(articles);
System.exit(0);
    }
}
