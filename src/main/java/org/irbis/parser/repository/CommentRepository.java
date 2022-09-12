package org.irbis.parser.repository;

import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByArticleAndNumber(Article article, Integer number);
    List<Comment> findAllByArticle(Article article);
}
