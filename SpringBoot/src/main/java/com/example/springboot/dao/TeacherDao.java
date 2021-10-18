package com.example.springboot.dao;

import com.example.springboot.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDao {

    public List<Teacher> selectAllTeacher();

    public void addTeacher(Teacher teacher);

    public Teacher selectById(String tno);

    public void updateTeacher(Teacher teacher);

    public void deleteById(String tno);

    public List<Teacher> selectBySno(String sno);

}
