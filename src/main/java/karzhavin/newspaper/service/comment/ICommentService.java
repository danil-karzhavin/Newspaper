package karzhavin.newspaper.service.comment;

import karzhavin.newspaper.model.comment.Comment;
import karzhavin.newspaper.model.comment.CommentDto;

import java.util.List;

public interface ICommentService {
    Comment getCommentById(Integer id);
    List<Comment> getAllCommentsByNewsId();
    Comment createComment(CommentDto commentDto);
    Comment updateComment(Integer commentId, CommentDto commentDto);
    Comment deleteCommentById(Integer id);
}
