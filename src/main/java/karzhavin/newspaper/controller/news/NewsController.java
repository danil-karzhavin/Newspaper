package karzhavin.newspaper.controller.news;

import karzhavin.newspaper.model.news.News;
import karzhavin.newspaper.model.news.NewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import karzhavin.newspaper.service.News.INewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    INewsService newsService;

    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    ResponseEntity<List<News>> GetAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{newsId}")
    ResponseEntity<News> getNewsById(@PathVariable("newsId") Integer newsId){
        return ResponseEntity.ok(newsService.getNewsById(newsId));
    }

    @GetMapping("/{authorId}")
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
    ResponseEntity<Object> deleteAllNewsByAuthorId(@PathVariable("authorId") Integer authorId){
        newsService.deleteAllNewsByAuthorId(authorId);
        return ResponseEntity.noContent().build();
    }
}
