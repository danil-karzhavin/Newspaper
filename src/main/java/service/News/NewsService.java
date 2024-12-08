package service.News;

import Exception.News.NewsNotFoundException;
import model.news.News;
import model.news.NewsDto;
import org.springframework.stereotype.Service;
import repository.news.INewsRepository;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {
    INewsRepository newsRepository;

    public NewsService(INewsRepository newsRepository) {
        this.newsRepository = newsRepository;
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
    public List<News> getAllNewsByAuthorId(Integer authorId) {
        return List.of();
    }

    @Override
    public News createNews(NewsDto newsDto) {
        News news = new News();
        BeanUtils.copyProperties(newsDto, news);
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
