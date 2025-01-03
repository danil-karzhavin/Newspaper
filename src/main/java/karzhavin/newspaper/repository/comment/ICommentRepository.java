package karzhavin.newspaper.repository.comment;

import karzhavin.newspaper.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByNewsId(Integer newsId);
}
