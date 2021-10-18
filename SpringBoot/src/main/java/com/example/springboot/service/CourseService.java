package com.example.springboot.service;

import com.example.springboot.pojo.Course;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    PageInfo<Course> selectAllCourse(int pageNum, int pageSize);

    public void addCourse(Course course);

    public Course selectById(String no);

    public void update(Course course);

    public void deleteById(String no);

    public List<Course> selectCourseTable(String sno);
}
