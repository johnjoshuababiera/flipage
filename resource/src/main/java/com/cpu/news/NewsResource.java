package com.cpu.news;


import com.cpu.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsResource {

    @Autowired
    private NewsService service;

    @PostMapping("/save")
    public News create(@RequestBody News news){
        return service.save(news);
    }

    @GetMapping("/fetchAllNews")
    public List<News> fetchAllNews(){
        return service.findAll();
    }

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment){
        return service.addComment(comment);
    }
}
