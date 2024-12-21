package karzhavin.newspaper.service.News;

import karzhavin.newspaper.Exception.News.NewsNotFoundException;
import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.news.NewsDto;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.service.user.IUserService;
import org.springframework.stereotype.Service;
import karzhavin.newspaper.repository.news.INewsRepository;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {
    INewsRepository newsRepository;
    IUserService userService;

    public NewsService(INewsRepository newsRepository, IUserService userService) {
        this.newsRepository = newsRepository;
        this.userService = userService;
    }

    @Override
    public News getNewsById(Integer newsId) {
        Optional<News> news = newsRepository.findById(newsId);

        if (news.isEmpty()) {
            throw new NewsNotFoundException("Not found New with such id");
        }
        return news.get();
    }

    @Override
    public List<News> getAllNews() {
        return List.of();
    }

    @Override
    public List<News> getNewsByAuthorId(Integer authorId) {
        return List.of();
    }

    @Override
    public News createNews(NewsDto newsDto) {
        News news = new News();

        User author = userService.getUserById(newsDto.getAuthorId());
        news.setAuthor(author);

        BeanUtils.copyProperties(newsDto, news);
        news.setCountLikes(0);
        news.setDateCreation(LocalDate.now());
        newsRepository.save(news);
        return news;
    }

    @Override
    public News updateNews(NewsDto newsDto) {
        News news = getNewsById(newsDto.getId());
        BeanUtils.copyProperties(newsDto, news);
        newsRepository.save(news);
        return news;
    }

    @Override
    public void deleteNewsById(Integer newsId) {
        if(!newsRepository.existsById(newsId)) {
            throw new NewsNotFoundException("Not found New with such id");
        }
        newsRepository.deleteById(newsId);
    }

    @Override
    public void deleteAllNewsByAuthorId(Integer authorId) {
    }
}
