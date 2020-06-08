package lt.codeacademy.SpringProject.repositories;

import lt.codeacademy.SpringProject.entities.Article;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Primary
@Repository
public class DbArticleDao {
    private JdbcTemplate jdbcTemplate;

    public DbArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Article mapToArticle(ResultSet resultSet, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getLong("article_id"));
        article.setAuthor(resultSet.getString("author"));
        article.setTitle(resultSet.getString("title"));
        article.setArticleContent(resultSet.getString("article_content"));
        return article;
    }
}
