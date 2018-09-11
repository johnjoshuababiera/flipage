package com.cpu.comment;

import com.cpu.comments.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findByNewsId(long id) throws Exception;
    List<Comment> findByPostId(long id);

    void removeById(Long id);
}
