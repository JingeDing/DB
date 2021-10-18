package com.example.springboot.dao;

import com.example.springboot.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentDao {

    List<Department> selectAll();

    List<Department> search();

    public void addDepartment(Department department);

    Department selectById(String dno);

    public void updateDepartment(Department department);

    public void deleteById(String dno);
}
