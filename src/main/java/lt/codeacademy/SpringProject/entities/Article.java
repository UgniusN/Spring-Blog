package lt.codeacademy.SpringProject.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name ="Articles")
public class Article {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "imageurl")
    @NotBlank(message = "Image must not be empty")
    private String imageurl;

    @Column(name = "title")
    @NotBlank(message = "Title must not be empty.")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Author must not be empty.")
    private String author;

    @Column(name = "article_content", columnDefinition = "NVARCHAR(MAX)")
    @NotBlank(message = "Content must not be empty.")
    private String articleContent;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "article")
    private List<Comment> comments;


    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
