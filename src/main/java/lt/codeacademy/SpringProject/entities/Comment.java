package lt.codeacademy.SpringProject.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "comment_id", updatable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;


    @Column(name = "commenttitle")
    private String commenttitle;

    @Column(name = "commentcontent")
    private String commentcontent;
}
