package com.cpu.topic;

import com.cpu.comments.Comment;
import com.cpu.comments.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicRepository repository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Topic addComment(Comment comment) {
        Topic topic = repository.getOne(comment.getArticleId());
        topic.getComments().add(commentRepository.save(comment));
        return repository.save(topic);
    }

    @Override
    public List<Topic> findAll() {
        return repository.findAll();
    }
}
