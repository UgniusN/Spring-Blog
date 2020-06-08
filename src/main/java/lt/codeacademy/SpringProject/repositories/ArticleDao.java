package lt.codeacademy.SpringProject.repositories;

import lt.codeacademy.SpringProject.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, Long> {
    List<Article> getArticleByTitle(String title);

    List<Article> findByAuthorIsContaining(String author);

}
