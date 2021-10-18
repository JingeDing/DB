package com.example.springboot.service;

import com.example.springboot.pojo.Department;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

public interface DepartmentService {
    PageInfo<Department> departmentPageInfo(int pageNum, int pageSize);

    public void addDepartment(Department department);

    public Department selectById(String dno);

    public void updateDepartment(Department department);

    public void deleteById(String dno);
}
