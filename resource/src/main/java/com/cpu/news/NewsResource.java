package com.cpu.news;


import com.cpu.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/findByDepartmentId")
    public List<News> fetchAllNews(@RequestParam long departmentId){
        return service.findByDepartmentId( departmentId);
    }
}
