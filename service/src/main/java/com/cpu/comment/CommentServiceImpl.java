package com.cpu.comment;

import com.cpu.comments.Comment;
import com.cpu.comments.CommentRepository;
import com.cpu.news.News;
import com.cpu.news.NewsRepository;
import com.cpu.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<Comment> findByNewsId(long id) throws Exception {
        News news = newsRepository.findById(id).get();
        List<Topic> topics = news.getTopics();
        if(topics.isEmpty()){
            throw new Exception("Please add a topic to news.");
        }
        List<Long> topicIds = news.getTopics().stream().map(n->n.getId()).collect(Collectors.toList());
        return repository.findCommentsInIds(topicIds);
    }

    @Override
    public List<Comment> findByPostId(long postId) {
        return repository.findCommentsByPostId(postId);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
