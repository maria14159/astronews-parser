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

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Storage {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public void save(HashMap<Article, List<Comment>> articles) {
        for (Article article : articles.keySet()) {
            Optional<Author> authorOptional = authorRepository.findByName(article.getAuthor().getName());
            Author author = authorOptional.isEmpty()
                    ? authorRepository.save(article.getAuthor())
                    : authorOptional.get();

            Optional<Article> articleOptional = articleRepository.findByAuthorAndTitle(author, article.getTitle());
            if(articleOptional.isEmpty()) {
                article.setAuthor(author);
                articleRepository.save(article);
            }

            for (Comment comment: articles.get(article)) {
                Comment commentOptional = commentRepository.findByArticle(article);
            }
        }
    }

}
