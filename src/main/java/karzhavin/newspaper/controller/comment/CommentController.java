package karzhavin.newspaper.controller.comment;

import karzhavin.newspaper.model.comment.Comment;
import karzhavin.newspaper.model.comment.CommentDto;
import karzhavin.newspaper.service.comment.ICommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
public class CommentController {
    ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{commentId}")
    ResponseEntity<CommentDto> getCommentDtoById(@PathVariable("commentId") Integer commentId){
        return ResponseEntity.ok(commentService.getCommentDtoById(commentId));
    }

    @GetMapping("/byNews/{newsId}")
    ResponseEntity<List<Comment>> getAllCommentsByNewsId(@PathVariable("newsId") Integer newsId){
        return ResponseEntity.ok(commentService.getAllCommentsByNewsId(newsId));
    }

    @PostMapping("/")
    ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.createComment(commentDto));
    }

    @PutMapping("/")
    ResponseEntity<Comment> updateCommentById(@RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateCommentById(commentDto));
    }

    @DeleteMapping("/{commentId}")
    void DeleteCommentById (@PathVariable("commentId") Integer commentId){
        commentService.deleteCommentById(commentId);
    }
}