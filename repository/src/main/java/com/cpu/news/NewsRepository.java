package com.cpu.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {

    @Query(value = "select n from News n where n.department.id=:id")
    List<News> findByDepartmentId(@Param("id") long id);
    List<News> findByUserId(long id);
}
