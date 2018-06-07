package com.cpu.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department save(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Department findOne(long id) {
        return repository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
