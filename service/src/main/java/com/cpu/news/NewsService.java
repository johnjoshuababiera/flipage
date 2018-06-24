package com.cpu.news;

import java.util.List;

public interface NewsService {
    News save(News news);

    List<News> findAll();

    News findOne(long id);

    void delete(long id);

    List<News> findByUserId(long id);


}
