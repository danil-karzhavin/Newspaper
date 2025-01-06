package karzhavin.newspaper.controller.news;

import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.news.NewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import karzhavin.newspaper.service.News.INewsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    INewsService newsService;

    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/getNews")
    ResponseEntity<List<News>> GetAllNews(@RequestBody Map<String, LocalDate> dates) {
        return ResponseEntity.ok(newsService.getAllNews(dates));
    }

    @GetMapping("/{newsId}")
    ResponseEntity<News> getNewsById(@PathVariable("newsId") Integer newsId){
        return ResponseEntity.ok(newsService.getNewsById(newsId));
    }

    @GetMapping("byAuthor/{authorId}")
    ResponseEntity<List<News>> getAllNewsByAuthorId(@PathVariable("authorId") Integer authorId){
        return ResponseEntity.ok(newsService.getNewsByAuthorId(authorId));
    }

    @PostMapping("/")
    ResponseEntity<News> createNews(@RequestBody NewsDto newsDto){
        return ResponseEntity.ok(newsService.createNews(newsDto));
    }

    @PutMapping("/")
    ResponseEntity<News> updateNews(@RequestBody NewsDto newsDto){
        return ResponseEntity.ok(newsService.updateNews(newsDto));
    }

    @DeleteMapping("/{newsId}")
    ResponseEntity<Object> deleteNewsById(@PathVariable("newsId") Integer newsId){
        newsService.deleteNewsById(newsId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{authorId}")
    void deleteAllNewsByAuthorId(@PathVariable("authorId") Integer authorId){
        newsService.deleteAllNewsByAuthorId(authorId);
    }
}
