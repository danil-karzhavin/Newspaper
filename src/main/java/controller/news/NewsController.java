package controller.news;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.News.INewsService;

@RestController
@RequestMapping("/news")
public class NewsController {
    INewsService newsService;

    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }
}
