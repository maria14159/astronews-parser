package org.irbis.parser.repository;

import org.irbis.parser.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByName(String name);
    Optional<Author> findByName(String name);
}
