package org.irbis.parser.repository;

import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByAuthorAndTitle(Author author, String title);

    List<Article> getAllByAuthor(Author author);

}
