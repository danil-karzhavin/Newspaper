package karzhavin.newspaper.repository.comment;

import karzhavin.newspaper.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
}
