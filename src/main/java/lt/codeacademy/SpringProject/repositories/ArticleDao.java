package lt.codeacademy.SpringProject.repositories;

import lt.codeacademy.SpringProject.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ArticleDao extends JpaRepository<Article, Long> {
}
