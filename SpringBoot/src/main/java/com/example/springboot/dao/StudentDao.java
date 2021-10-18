package com.example.springboot.dao;

import com.example.springboot.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    public List<Student> queryAll();

    public Student queryById(String id);

    public List<Student> selectAll();

    public void addStudent(Student student);

    public void updateStudent(Student student);

    public void deleteById(String sno);

    public List<Student> selectByCno(String cno);

}
