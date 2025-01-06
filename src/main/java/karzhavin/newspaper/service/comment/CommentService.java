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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    ICommentRepository commentRepository;
    IUserService userService;
    INewsService newsService;

    public CommentService(ICommentRepository commentRepository, IUserService userService, INewsService newsService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.newsService = newsService;
    }

    @Override
    public Comment getCommentById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new CommentNotFoundException("Not found comment with such id");
        }
        return comment.get();
    }

    @Override
    public CommentDto getCommentDtoById(Integer id) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(getCommentById(id), commentDto);
        return commentDto;
    }

    @Override
    public List<Comment> getAllCommentsByNewsId(Integer newsId) {
        return commentRepository.findAllByNewsId(newsId);
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        if (commentDto.getAuthorId() == null || commentDto.getNewsId() == null) {
            throw new CommentDtoException("Field 'authorId' or (and) 'newsId' is null");
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment, new String[]{"id"});

        User author = userService.getUserById(commentDto.getAuthorId());
        News news = newsService.getNewsById(commentDto.getNewsId());

        comment.setAuthor(author);
        comment.setNews(news);

        commentRepository.save(comment);
        BeanUtils.copyProperties(comment, commentDto);
        return commentDto;
    }

    @Override
    public Comment updateCommentById(CommentDto commentDto) {
        if (commentDto.getId() == null) {
            throw new CommentDtoException("Field 'id' is null");
        }
        Comment comment = getCommentById(commentDto.getId());
        BeanUtils.copyProperties(commentDto, comment, new String[]{"id", "newsId", "authorId"});
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }
}
