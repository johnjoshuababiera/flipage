package com.cpu.news;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<News> newsList =repository.findAll();
        removeUnactiveComments(newsList);
        return newsList;
    }

    @Override
    public News findOne(long id) {
        News news = repository.findById(id).get();
        filterComments(news);
        return news;
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }


    @Override
    public List<News> findByUserId(long id){
        List<News> newsList = repository.findByUserId(id);
        newsList.sort(Comparator.comparingLong(AuditTrail::getDateCreated).reversed());
        removeUnactiveComments(newsList);
        return newsList;
    }

    @Override
    public List<News> findByDepartmentId(long departmentId) {
        List<News> news = repository.findByDepartmentId(departmentId);
        news.sort(Comparator.comparingLong(AuditTrail::getDateCreated).reversed());
        removeUnactiveComments(news);
        return news;
    }

    private void removeUnactiveComments(List<News> newsList){
        newsList.forEach(news -> filterComments(news));
    }

    private void filterComments(News news) {
        news.getTopics().forEach(topic -> {
            topic.setComments(topic.getComments().stream().filter(Comment::isActive).collect(Collectors.toList()));
        });
    }
}
