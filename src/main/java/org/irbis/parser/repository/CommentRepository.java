package org.irbis.parser.repository;

import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Article> getAllByArticle(Article article);
}
