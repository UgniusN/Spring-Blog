package lt.codeacademy.SpringProject.services;


import lt.codeacademy.SpringProject.controller.ArticleNotFoundException;
import lt.codeacademy.SpringProject.entities.Article;
import lt.codeacademy.SpringProject.entities.Comment;
import lt.codeacademy.SpringProject.repositories.CommentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> getAllComents() {
        return commentDao.findAll();
    }

    public Comment saveOrUpdate(Comment comment) {
        return commentDao.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentDao.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id: " + id + " was not found"));
    }
}
