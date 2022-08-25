package org.irbis.parser.service;

import lombok.RequiredArgsConstructor;
import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.repository.ArticleRepository;
import org.irbis.parser.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    public List<Article> getTopTenArticles() {
        return new ArrayList<>(articleRepository.findAll());
    }

    public List<Article> getAllByAuthor(String name) {
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        if (optionalAuthor.isEmpty()) {
            return List.of();
        }
        Author author = optionalAuthor.get();
        return articleRepository.getAllByAuthor(author);
    }

    public boolean addAuthor(Author author) {
        if (authorRepository.existsByName(author.getName())) {
            return false;
        }
        authorRepository.save(author);
        return true;
    }
}
