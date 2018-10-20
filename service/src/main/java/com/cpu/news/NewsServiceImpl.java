package com.cpu.news;

import com.cpu.AuditTrail;
import com.cpu.comments.Comment;
import com.cpu.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public News save(News news) {
        if(news.getId()==null){
            news.setDateCreated(System.currentTimeMillis());
        }
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
    public void delete(long id) {
        repository.deleteById(id);
    }


    @Override
    public List<News> findByUserId(long id){
        return repository.findByUserId(id);
    }

    @Override
    public List<News> findByDepartmentId(long departmentId) {
        List<News> news = repository.findByDepartmentId(departmentId);
        news.sort(Comparator.comparingLong(AuditTrail::getDateCreated).reversed());
        return news;
    }
}
