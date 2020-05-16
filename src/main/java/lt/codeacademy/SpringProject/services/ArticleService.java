package lt.codeacademy.SpringProject.services;

import lt.codeacademy.SpringProject.Article;
import lt.codeacademy.SpringProject.controller.ArticleNotFoundException;
import lt.codeacademy.SpringProject.repositories.ArticleDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArticleService {

    private ArticleDao articleDao;

    public Article getArticle(Long id) {
        return articleDao.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id: " + id + " was not found"));
    }

    public ArticleService(ArticleDao articleRepository) {
        this.articleDao = articleRepository;
    }

    public void deleteArticle(Long id) {
        articleDao.delete(articleDao.getOne(id));
    }

    public Article createOrUpdateArticle(Article article) {
        return articleDao.save(article);
    }

    public List<Article> getArticles() {
        return articleDao.findAll();
    }
}
