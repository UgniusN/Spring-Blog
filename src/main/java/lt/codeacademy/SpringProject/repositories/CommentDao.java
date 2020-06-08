package lt.codeacademy.SpringProject.repositories;

import lt.codeacademy.SpringProject.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Long> {
}
