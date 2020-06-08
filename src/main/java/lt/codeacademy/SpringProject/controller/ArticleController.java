package lt.codeacademy.SpringProject.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import lt.codeacademy.SpringProject.entities.Article;
import lt.codeacademy.SpringProject.entities.Comment;
import lt.codeacademy.SpringProject.entities.User;
import lt.codeacademy.SpringProject.services.ArticleService;
import lt.codeacademy.SpringProject.services.CommentService;
import lt.codeacademy.SpringProject.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {


    private ArticleService articleService;
    private CommentService commentService;
    private UserService userService;

    public ArticleController(ArticleService articleService, CommentService commentService, UserService userService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public String getArticlesByPage(@RequestParam(defaultValue = "0") int pageNumber, Model model, @AuthenticationPrincipal User user) {
        Page<Article> articles = articleService.getAllArticlesPaginated(pageNumber);
        model.addAttribute("articles",articles);
        model.addAttribute("user", user);
        model.addAttribute("article",new Article());
        model.addAttribute("pageNumber",pageNumber);
        model.addAttribute("hasNextPage",articles.hasNext());
        return "articles";
    }

    @GetMapping("/controlpanel")
    public String getControlPanelHome() {
        return "controlpanelhome";
    }

    @GetMapping("/controlpanel/managearticles")
    public String getArticleInfo(Model model) {
        List<Article> articles = articleService.getArticles();
        model.addAttribute("articles", articles);
        return "controlpanelmanagearticles";
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
        return "controlpaneleditarticle";
    }

    @GetMapping("/controlpanel/create")
    public String getNewArticleForm(Model model) {
        model.addAttribute("article",new Article());
        return "controlpanelnewarticle";
    }

    @PostMapping("/controlpanel/submit")
    public String submitArticle(@ModelAttribute("article") @Valid Article article, BindingResult errors, Model model ) {
        if (errors.hasErrors()) {       return "articleedit";   }
        articleService.createOrUpdateArticle(article);
        Comment comment = new Comment();
        model.addAttribute("article",article);
        model.addAttribute("comment",comment);
        model.addAttribute("comments",article.getComments());
        return "article";
    }

    @PostMapping("/{id}/submitcomment")
    public String submitComment(
            @ModelAttribute("comment") Comment comment,@PathVariable Long id,
            Model model
    ) {
        Article article = articleService.getArticle(id);
        comment.setArticle(article);
        comment.setId(null);
        commentService.saveOrUpdate(comment);
        model.addAttribute("article",article);
        List<Article> articles = articleService.getArticles();
        //model.addAttribute("article",new Article());
        model.addAttribute("articles", articles);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String getOneArticle(@PathVariable Long id, Model model) {
        Comment comment = new Comment();
        Article article = articleService.getArticle(id);
        model.addAttribute("article",article);
        model.addAttribute("comment",comment);
        model.addAttribute("comments",article.getComments());
        return "article";
    }

    @PostMapping(value = "/byTitle")
    public String getArticlesByTitle(Article article, Model model) {
        model.addAttribute("article",new Article());
        //model.addAttribute("comments",comments);
        List<Article> articles = articleService.getArticlesByTitle(article.getTitle());
        model.addAttribute("articles",articles);
        return "articles";
    }


    @GetMapping("/controlpanel/managecomments/{id}/edit")
    public String getUpdateCommentForm(@PathVariable Long id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "controlpaneleditcomment";
    }

    @GetMapping("/controlpanel/managecomments")
    public String getCommentInfo(Model model) {
        List<Comment> comments = commentService.getAllComents();
        model.addAttribute("comments", comments);
        return "controlpanelmanagecomments";
    }

    @GetMapping("/controlpanel/manageusers")
    public String getUserInfo(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "controlpanelmanageusers";
    }
}
