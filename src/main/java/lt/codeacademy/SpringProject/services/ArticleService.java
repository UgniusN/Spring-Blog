package lt.codeacademy.SpringProject.services;

import lt.codeacademy.SpringProject.entities.Article;
import lt.codeacademy.SpringProject.controller.ArticleNotFoundException;
import lt.codeacademy.SpringProject.entities.Comment;
import lt.codeacademy.SpringProject.repositories.ArticleDao;
import lt.codeacademy.SpringProject.repositories.DbArticleDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    private DbArticleDao dbArticleDao;
    private ArticleDao articleDao;

    public Article getArticle(Long id) {
        return articleDao.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id: " + id + " was not found"));
    }

    public ArticleService(ArticleDao articleRepository, DbArticleDao dbArticleDao) {
        this.dbArticleDao = dbArticleDao;
        this.articleDao = articleRepository;
    }

    public void deleteArticle(Long id) {
        articleDao.delete(articleDao.getOne(id));
    }

    public Article createOrUpdateArticle(Article article) {
        return articleDao.save(article);
    }

    public List<Comment> comments() {
        return null;
    }

    public List<Article> getArticles() {
        return articleDao.findAll();
    }

    public List<Article> getArticlesByTitle(String author) {
        List<Article> articles = articleDao.findByAuthorIsContaining(author);
        return articles;
    }


    public Page<Article> getAllArticlesPaginated(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,3);
        return articleDao.findAll(pageable);
    }

    //public Page<Article> findAllPageable()
}
