package com.cpu.topic;

import com.cpu.comments.Comment;
import com.cpu.post.Post;
import com.cpu.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/topic")
public class TopicResource {

    @Autowired
    private TopicService service;


    @PostMapping("/addComment")
    public Topic addComment(@RequestBody Comment comment){
        return service.addComment(comment);
    }
}

