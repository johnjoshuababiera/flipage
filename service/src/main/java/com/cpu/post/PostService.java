package com.cpu.post;

import com.cpu.comments.Comment;

import java.util.List;

public interface PostService {
    Post save(Post post);
    List<Post> findAll();
    Post findOne(long id);
    void delete(long id);
    Post addComment(Comment comment);
}
