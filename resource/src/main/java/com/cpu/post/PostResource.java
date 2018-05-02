package com.cpu.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostResource {

    @Autowired
    private PostService service;

    @PostMapping("/save")
    public Post create(@RequestBody Post post){
        return service.save(post);
    }
}
