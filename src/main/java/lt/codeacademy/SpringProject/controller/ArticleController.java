package lt.codeacademy.SpringProject.controller;


import lt.codeacademy.SpringProject.Article;
import lt.codeacademy.SpringProject.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/articles")
public class ArticleController {


    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String getAllArticles(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "articles";
    }

    @GetMapping("/controlpanel")
    public String getArticleInfo(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "articlecontrol";
    }

    @GetMapping("/controlpanel/{id}/delete")
    public String deleteArticle(@PathVariable Long id, Model model) {
        articleService.deleteArticle(id);
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "articlecontrol";
    }


    @GetMapping("/controlpanel/{id}/edit")
    public String getUpdateProductForm(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "articleedit";
    }

    @GetMapping("/controlpanel/create")
    public String getNewArticleForm(Model model) {
        model.addAttribute("article",new Article());
        return "articleedit";
    }

    @PostMapping("/controlpanel/submit")
    public String submitArticle(@ModelAttribute("article") Article article, Model model ) {
        articleService.createOrUpdateArticle(article);
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles",articles);
        return "articlecontrol";
    }

    @GetMapping("/{id}")
    public String getOneArticle(@PathVariable Long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article",article);
        return "article";
    }
}
