package com.cpu.department;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);
    List<Department> findAll();
    Department findOne(long id);
    boolean checkUsed(long id);
    void delete(long id);
}
