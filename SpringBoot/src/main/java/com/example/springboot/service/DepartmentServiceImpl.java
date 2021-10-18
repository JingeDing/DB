package com.example.springboot.service;

import com.example.springboot.dao.DepartmentDao;
import com.example.springboot.pojo.Department;
import com.example.springboot.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao dd;

    @Override
    public PageInfo<Department> departmentPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> list = dd.selectAll();
        return new PageInfo<Department>(list);
    }

    @Override
    public void addDepartment(Department department) {
        dd.addDepartment(department);
    }

    @Override
    public Department selectById(String dno) {
        return dd.selectById(dno);
    }

    @Override
    public void updateDepartment(Department department) {
        dd.updateDepartment(department);
    }

    @Override
    public void deleteById(String dno) {
        dd.deleteById(dno);
    }
}
