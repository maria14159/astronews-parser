package org.irbis.parser.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@AllArgsConstructor
public class CmdLiner implements CommandLineRunner {

    private final SiteLoader loader;
    private final SiteParser parser;
    private final Storage storage;
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    public void run(String... args) throws Exception {
//        List<String> urls = List.of(
//                "https://habr.com/ru/hub/read/",
//                "https://habr.com/ru/hub/pm/",
//                "https://habr.com/ru/hub/python/",
//                "https://habr.com/ru/hub/java/",
//                "https://habr.com/ru/hub/ruby/",
//                "https://habr.com/ru/hub/cpp/",
//                "https://habr.com/ru/hub/haskell/",
//                "https://habr.com/ru/hub/career/",
//                "https://habr.com/ru/hub/lib/",
//                "https://habr.com/ru/hub/ui/"
//        );
//
//        List<Runnable> tasks = new ArrayList<>();
//        for (String url : urls) {
//            tasks.add(() -> {
//                log.info("start work with {}", url);
//                String load = loader.load(url);
//                List<Article> articles = parser.parse(load);
//                storage.save(articles);
//            });
//        }
//
//        log.info("loading start");
//        tasks.forEach(executorService::submit);
//        log.info("loading end");
//
//        executorService.shutdown();

        System.exit(0);
    }

}
