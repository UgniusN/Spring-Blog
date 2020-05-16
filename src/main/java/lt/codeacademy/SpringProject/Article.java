package lt.codeacademy.SpringProject;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="Articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "article_content")
    private String articleContent;
}
