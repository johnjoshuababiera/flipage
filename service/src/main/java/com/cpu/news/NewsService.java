package com.cpu.news;

import com.cpu.comments.Comment;

import java.util.List;

public interface NewsService {
    News save(News news);
    List<News> findAll();
    News findOne(long id);
    void delete(long id);
}
