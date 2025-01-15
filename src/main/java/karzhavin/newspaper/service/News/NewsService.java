package karzhavin.newspaper.service.News;

import karzhavin.newspaper.Exception.News.NewsDtoException;
import karzhavin.newspaper.Exception.News.NewsException;
import karzhavin.newspaper.Exception.News.NewsNotFoundException;
import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.news.NewsDto;
import karzhavin.newspaper.model.user.User;
import karzhavin.newspaper.service.user.IUserService;
import org.springframework.stereotype.Service;
import karzhavin.newspaper.repository.news.INewsRepository;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
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
    public List<News> getAllNews(Map<String, LocalDate> dates) {
        LocalDate startDate = dates.get("startDate");
        LocalDate endDate = dates.get("endDate");
        if (startDate == null || endDate == null) {
            throw new NewsException("Field 'startDate' or 'endDate' is null in Map<String, LocalDate> dates");
        }
        return newsRepository.findAllBetweenDates(startDate, endDate);
    }

    @Override
    public List<News> getNewsByAuthorId(Integer authorId) {
        return newsRepository.findAllByAuthorIdOrderByDateCreationDesc(authorId);
    }

    @Override
    public News createNews(NewsDto newsDto) {
        if (newsDto.getAuthorId() == null) {
            throw new NewsDtoException("Field 'authorId' is null");
        }
        News news = new News();

        User author = userService.getUserById(newsDto.getAuthorId());
        news.setAuthor(author);

        BeanUtils.copyProperties(newsDto, news, new String[]{"id"});

        if (newsDto.getImageBase64() != null && !newsDto.getImageBase64().isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(newsDto.getImageBase64());
            news.setImage(imageBytes);
        }
        newsRepository.save(news);
        return news;
    }

    @Override
    public News updateNews(NewsDto newsDto) {
        if (newsDto.getId() == null) {
            throw new NewsDtoException("Field 'id' is null");
        }
        News news = getNewsById(newsDto.getId());
        BeanUtils.copyProperties(newsDto, news, new String[]{"id"});

        if (newsDto.getImageBase64() != null && !newsDto.getImageBase64().isEmpty()) {
            byte[] imageBytes = Base64.getDecoder().decode(newsDto.getImageBase64());
            news.setImage(imageBytes);
        }
        newsRepository.save(news);
        return news;
    }

    @Override
    public void deleteNewsById(Integer newsId) {
        if (!newsRepository.existsById(newsId)) {
            throw new NewsNotFoundException("Not found New with such id");
        }
        newsRepository.deleteById(newsId);
    }

    @Override
    public void deleteAllNewsByAuthorId(Integer authorId) {
    }
}
