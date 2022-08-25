package org.irbis.parser.service;

import lombok.RequiredArgsConstructor;
import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.repository.ArticleRepository;
import org.irbis.parser.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Storage {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void save(List<Article> articles) {
        for (Article article : articles) {
            Optional<Author> authorOptional = authorRepository.findByName(article.getAuthor().getName());
            Author author = authorOptional.isEmpty()
                    ? authorRepository.save(article.getAuthor())
                    : authorOptional.get();

            Optional<Article> articleOptional = articleRepository.findByAuthorAndTitle(author, article.getTitle());
            if(articleOptional.isEmpty()) {
                article.setAuthor(author);
                articleRepository.save(article);
            }
        }
    }

}
