package com.cpu.department;

import com.cpu.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentResource {

    @Autowired
    private DepartmentService service;


    @GetMapping("/findAll")
    public List<Department> fetchAllNews(){
        return service.findAll();
    }
}
