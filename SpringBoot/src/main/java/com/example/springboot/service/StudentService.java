package com.example.springboot.service;

import com.example.springboot.pojo.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    public List<Student> queryAll();

    public Student queryById(String id);

    PageInfo<Student> studentPageInfo(int pageNum, int pageSize);

    public void addStudent(Student student);

    public void updateStudent(Student student);

    public void deleteById(String sno);

    public List<Student> selectByCno(String cno);

}
