package karzhavin.newspaper.service.comment;

import karzhavin.newspaper.model.comment.Comment;
import karzhavin.newspaper.model.comment.CommentDto;

import java.util.List;

public interface ICommentService {
    Comment getCommentById(Integer id);
    CommentDto getCommentDtoById(Integer id);
    List<Comment> getAllCommentsByNewsId(Integer newsId);
    CommentDto createComment(CommentDto commentDto);
    Comment updateCommentById(CommentDto commentDto);
    void deleteCommentById(Integer id);
}
