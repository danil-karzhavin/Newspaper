package karzhavin.newspaper.service.comment;

import karzhavin.newspaper.Exception.comment.CommentNotFoundException;
import karzhavin.newspaper.model.comment.Comment;
import karzhavin.newspaper.model.comment.CommentDto;
import karzhavin.newspaper.repository.comment.ICommentRepository;

import java.util.List;
import java.util.Optional;

public class CommentService implements ICommentService{
    ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()){
            throw new CommentNotFoundException("Not found comment with such id");
        }
        return comment.get();
    }

    @Override
    public List<Comment> getAllCommentsByNewsId() {
        return List.of();
    }

    @Override
    public Comment createComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public Comment updateComment(Integer commentId, CommentDto commentDto) {
        return null;
    }

    @Override
    public Comment deleteCommentById(Integer id) {
        return null;
    }
}
