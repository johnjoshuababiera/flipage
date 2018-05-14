package com.cpu.news;

import com.cpu.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {


    @Autowired
    private NewsRepository repository;



    @Override
    public News save(News news) {
        return repository.save(news);
    }

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public News findOne(long id) {
        return repository.findById(id).get();
    }

    @Override
    public News addComment(long newsId, Comment comment) {
        News news = repository.getOne(newsId);
        news.getComments().add(comment);
        return save(news);
    }
}
