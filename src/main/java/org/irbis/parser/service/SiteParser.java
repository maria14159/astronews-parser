package org.irbis.parser.service;

import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.model.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteParser {

    public Article parseArticle(String html) {

        Document document = Jsoup.parse(html);
        var article = document.select(".news-page");

        String title = article.select("p.name").text();
        String text = article.select("p").stream()
                .filter(e -> !e.hasClass("name") && !e.html().contains("?page=user"))
                .map(Element::text)
                .collect(Collectors.joining());
        String author = article.select("i a").text();
        String viewsComments = article.select(".counts").text(); //vies commentsCount


        String[] viewsAndComments = viewsComments.split(" ");

        return new Article(
                title,
                text,
                new Author(author),
                Integer.parseInt(viewsAndComments[0]),
                Integer.parseInt(viewsAndComments[1])
        );
    }

    public List<Comment> parseComments(String html, Article article){
        List<Comment> commentsList = new ArrayList();

        Document document = Jsoup.parse(html);

        var comments = document.select(".comment-item");

        for (var comment: comments) {
            String author = comment.select("name").text();
            String text = comment.select("p").text();

            commentsList.add(new Comment(article, new Author(author), text));
        }
        return commentsList;
    }
    public List<String> parseMain(String html, String url) {
        Document document = Jsoup.parse(html);
        Elements articles = document.select("div.item");
        List<String> linkList = new ArrayList<>();

        for (Element article : articles) {
            String link = article.select("p a").attr("href");

            if(link.contains(url)){
                linkList.add(link);
            }
            else{
                linkList.add(url+link);
            }
        }
        return linkList;
    }
}
