package org.irbis.parser.service;

import lombok.RequiredArgsConstructor;
import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.model.Comment;
import org.irbis.parser.repository.ArticleRepository;
import org.irbis.parser.repository.AuthorRepository;
import org.irbis.parser.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Area;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Storage {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    private final CommentRepository commentRepository;

//    @Transactional
//    public void save(Article article, List<Comment> comments) {
//
//        Author ArticleAuthor = saveAuthor(article.getAuthor());
//
//        Article articleInBase = saveArticle(article, ArticleAuthor);
//
//        for (Comment comment : comments) {
//
//            Author commentAuthor = saveAuthor(comment.getAuthor());
//
//            Optional<Comment> commentOptional = commentRepository.findByArticleAndNumber(articleInBase, comment.getNumber());
//
//            if (commentOptional.isEmpty()) {
//                comment.setAuthor(commentAuthor);
//                comment.setArticle(articleInBase);
//                commentRepository.save(comment);
//            }
//        }
//    }

    @Transactional
    public void save(HashMap<Article, List<Comment>> articles) {
        for (Map.Entry<Article, List<Comment>> entry : articles.entrySet()) {
            Article article = entry.getKey();
            List<Comment> comments = entry.getValue();

            Author ArticleAuthor = saveAuthor(article.getAuthor());

            Article articleInBase = saveArticle(article, ArticleAuthor);

            for (Comment comment: comments) {

                Author commentAuthor = saveAuthor(comment.getAuthor());

                Optional<Comment> commentOptional = commentRepository.findByArticleAndNumber(articleInBase, comment.getNumber());

                if (commentOptional.isEmpty()){
                    comment.setAuthor(commentAuthor);
                    comment.setArticle(articleInBase);
                    commentRepository.save(comment);
                }
            }
        }
    }

    public Author saveAuthor(Author author){
        Optional<Author> authorOptional = authorRepository.findByName(author.getName());
        return authorOptional.isEmpty()
                ? authorRepository.save(author)
                : authorOptional.get();
    }


    public Article saveArticle(Article article, Author author) {
        Optional<Article> articleOptional = articleRepository.findByAuthorAndTitle(author, article.getTitle());
        if(articleOptional.isEmpty()) {
            article.setAuthor(author);
            return articleRepository.save(article);
        }
        else{
            return articleOptional.get();
        }
    }

}
