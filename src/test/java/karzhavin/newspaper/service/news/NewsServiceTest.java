package karzhavin.newspaper.service.news;

import karzhavin.newspaper.Exception.News.NewsException;
import karzhavin.newspaper.Exception.News.NewsNotFoundException;
import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.repository.news.INewsRepository;
import karzhavin.newspaper.service.News.NewsService;
import karzhavin.newspaper.service.user.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewsServiceTest {

    private NewsService newsService;
    private INewsRepository newsRepository;
    private IUserService userService;

    @BeforeEach
    void setUp() {
        newsRepository = mock(INewsRepository.class);
        userService = mock(IUserService.class);

        newsService = new NewsService(newsRepository, userService);
    }

    @Test
    void testGetNewsById_Found() {
        int newsId = 1;
        News news = new News();
        news.setId(newsId);
        when(newsRepository.findById(newsId)).thenReturn(Optional.of(news));

        News result = newsService.getNewsById(newsId);

        assertNotNull(result);
        assertEquals(newsId, result.getId());
        verify(newsRepository, times(1)).findById(newsId);
    }

    @Test
    void testGetNewsById_NotFound() {
        int newsId = 1;
        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        assertThrows(NewsNotFoundException.class, () -> newsService.getNewsById(newsId));
        verify(newsRepository, times(1)).findById(newsId);
    }

    @Test
    void testGetAllNews_ValidDates() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        Map<String, LocalDate> dates = Map.of("startDate", startDate, "endDate", endDate);
        List<News> newsList = List.of(new News());

        when(newsRepository.findAllBetweenDates(startDate, endDate)).thenReturn(newsList);

        List<News> result = newsService.getAllNews(dates);

        assertNotNull(result);
        assertEquals(newsList.size(), result.size());
        verify(newsRepository, times(1)).findAllBetweenDates(startDate, endDate);
    }

    @Test
    void testDeleteNewsById_Success() {
        int newsId = 1;
        when(newsRepository.existsById(newsId)).thenReturn(true);

        newsService.deleteNewsById(newsId);
        verify(newsRepository, times(1)).deleteById(newsId);
    }

    @Test
    void testDeleteNewsById_NotFound() {
        int newsId = 1;
        when(newsRepository.existsById(newsId)).thenReturn(false);

        assertThrows(NewsNotFoundException.class, () -> newsService.deleteNewsById(newsId));
        verify(newsRepository, never()).deleteById(newsId);
    }
}
