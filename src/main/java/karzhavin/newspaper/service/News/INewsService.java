package karzhavin.newspaper.service.News;

import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.news.NewsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface INewsService {
    News getNewsById(Integer newsId);
    List<News> getAllNews(Map<String, LocalDate> dates);
    List<News> getNewsByAuthorId(Integer authorId);

    News createNews(NewsDto newsDto);
    News updateNews(NewsDto newsDto);

    void deleteNewsById(Integer newsId);
    void deleteAllNewsByAuthorId(Integer authorId);
}
