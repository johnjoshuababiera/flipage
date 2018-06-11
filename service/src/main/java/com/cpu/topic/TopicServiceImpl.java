package com.cpu.topic;

import com.cpu.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicRepository repository;

    @Override
    public Topic addComment(Comment comment) {
        Topic topic = repository.getOne(comment.getArticleId());
        topic.getComments().add(comment);
        return repository.save(topic);
    }
}
