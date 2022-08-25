package org.irbis.parser.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(generator = "author_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "author_id_gen", sequenceName = "author_id_seq", allocationSize = 1)
    private Long id;
    private String name;

    public Author(String name) {
        this.name = name;
    }
}
