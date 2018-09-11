package com.cpu.topic;

import com.cpu.comments.Comment;

import java.util.List;

public interface TopicService {
    Topic addComment(Comment comment);
    List<Topic> findAll();
}
