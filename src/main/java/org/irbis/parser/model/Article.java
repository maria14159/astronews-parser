package org.irbis.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(generator = "article_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "article_id_gen", sequenceName = "article_id_seq", allocationSize = 1)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String text;
    private Integer views;
    private Integer commentsCount;


    public Article(String title, String text, Author author, Integer views, Integer commentsCount) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.views = views;
        this.commentsCount = commentsCount;
    }
}
