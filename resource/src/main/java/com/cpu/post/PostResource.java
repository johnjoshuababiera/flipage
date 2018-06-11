package com.cpu.post;

import com.cpu.comments.Comment;
import com.cpu.post.Post;
import com.cpu.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/post")
public class PostResource {

    @Autowired
    private PostService service;

    @PostMapping("/save")
    public Post create(@RequestBody Post post){
        return service.save(post);
    }

    @GetMapping("/fetchAllPost")
    public List<Post> fetchAllPost(){
        return service.findAll();
    }

    @PostMapping("/addComment")
    public Post addComment(@RequestBody Comment comment){
        return service.addComment(comment);
    }
}
