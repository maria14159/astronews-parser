package org.irbis.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(generator = "comment_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_id_gen", sequenceName = "comment_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(columnDefinition="text")
    private String text;

    private Integer number;

    public Comment(Article article, Integer number, Author author, String text) {
        this.article = article;
        this.author = author;
        this.text = text;
        this.number = number;
    }
}
