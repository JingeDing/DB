package com.example.springboot.service;

import com.example.springboot.pojo.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TeacherService {
    PageInfo<Teacher> selectAllTeacher(int pageNum, int pageSize);

    public void addTeacher(Teacher teacher);

    public Teacher selectById(String tno);

    public void updateTeacher(Teacher teacher);

    public void deleteById(String tno);

    public List<Teacher> selectBySno(String sno);

}
