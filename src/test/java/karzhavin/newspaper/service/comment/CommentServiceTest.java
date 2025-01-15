package karzhavin.newspaper.service.comment;

import karzhavin.newspaper.Exception.comment.CommentDtoException;
import karzhavin.newspaper.Exception.comment.CommentNotFoundException;
import karzhavin.newspaper.model.comment.Comment;
import karzhavin.newspaper.model.comment.CommentDto;
import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.repository.comment.ICommentRepository;
import karzhavin.newspaper.service.News.INewsService;
import karzhavin.newspaper.service.user.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Mock
    private ICommentRepository commentRepository;

    @Mock
    private IUserService userService;

    @Mock
    private INewsService newsService;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentById_Success() {
        Comment comment = new Comment();
        comment.setId(1);
        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        Comment result = commentService.getCommentById(1);

        assertEquals(1, result.getId());
        verify(commentRepository, times(1)).findById(1);
    }

    @Test
    void testGetCommentById_NotFound() {
        when(commentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> commentService.getCommentById(1));
        verify(commentRepository, times(1)).findById(1);
    }

    @Test
    void testGetCommentDtoById_Success() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setText("Test content");
        CommentDto expectedCommentDto = new CommentDto();
        expectedCommentDto.setId(1);
        expectedCommentDto.setText("Test content");

        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        CommentDto result = commentService.getCommentDtoById(1);

        assertEquals(expectedCommentDto.getId(), result.getId());
        assertEquals(expectedCommentDto.getText(), result.getText());
    }

    @Test
    void testGetAllCommentsByNewsId_Success() {
        Comment comment = new Comment();
        comment.setId(1);
        when(commentRepository.findAllByNewsId(1)).thenReturn(Collections.singletonList(comment));

        var result = commentService.getAllCommentsByNewsId(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        verify(commentRepository, times(1)).findAllByNewsId(1);
    }

    @Test
    void testCreateComment_Success() {
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthorId(1);
        commentDto.setNewsId(1);
        commentDto.setText("Test content");

        User user = new User();
        News news = new News();

        when(userService.getUserById(1)).thenReturn(user);
        when(newsService.getNewsById(1)).thenReturn(news);

        CommentDto result = commentService.createComment(commentDto);

        assertEquals(commentDto.getText(), result.getText());
        verify(userService, times(1)).getUserById(1);
        verify(newsService, times(1)).getNewsById(1);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testCreateComment_MissingAuthorId() {
        CommentDto commentDto = new CommentDto();
        commentDto.setNewsId(1);

        assertThrows(CommentDtoException.class, () -> commentService.createComment(commentDto));
        verifyNoInteractions(userService, newsService, commentRepository);
    }

    @Test
    void testUpdateCommentById_Success() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);
        commentDto.setText("Updated content");

        Comment comment = new Comment();
        comment.setId(1);

        when(commentRepository.findById(1)).thenReturn(Optional.of(comment));

        Comment updatedComment = commentService.updateCommentById(commentDto);

        assertEquals(commentDto.getText(), updatedComment.getText());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testUpdateCommentById_MissingId() {
        CommentDto commentDto = new CommentDto();

        assertThrows(CommentDtoException.class, () -> commentService.updateCommentById(commentDto));
        verifyNoInteractions(commentRepository);
    }

    @Test
    void testDeleteCommentById_Success() {
        commentService.deleteCommentById(1);

        verify(commentRepository, times(1)).deleteById(1);
    }
}