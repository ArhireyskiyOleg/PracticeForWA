package org.example.Controllers;

import org.example.Entities.News;
import org.example.Services.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

    @GetMapping(produces = "application/json")
    public List<News> getNews(){
        NewsService newsService = new NewsService();
        return newsService.getListNews();
    }
}
