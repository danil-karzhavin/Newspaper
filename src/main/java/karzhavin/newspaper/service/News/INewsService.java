package karzhavin.newspaper.service.News;

import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.news.NewsDto;

import java.util.List;

public interface INewsService {
    News getNewsById(Integer newsId);
    List<News> getAllNews();
    List<News> getNewsByAuthorId(Integer authorId);

    News createNews(NewsDto newsDto);
    News updateNews(NewsDto newsDto);

    void deleteNewsById(Integer newsId);
    void deleteAllNewsByAuthorId(Integer authorId);
}
