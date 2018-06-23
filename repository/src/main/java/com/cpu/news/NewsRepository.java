package com.cpu.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {

    List<News> findByDepartmentId(long id);
}
