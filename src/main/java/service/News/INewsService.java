package service.News;

import model.news.News;
import model.news.NewsDto;

import java.util.List;

public interface INewsService {
    News getNewsById(Integer newsId);
    List<News> getAllNews();
    List<News>  getAllNewsByAuthorId(Integer authorId);

    News createNews(NewsDto newsDto);
    News updateNews(NewsDto newsDto);

    void deleteNewsById(Integer newsId);
    void deleteAllNewsByAuthorId(Integer authorId);
}
