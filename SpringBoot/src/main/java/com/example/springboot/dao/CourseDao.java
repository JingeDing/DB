package com.example.springboot.dao;

import com.example.springboot.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {

    public List<Course> selectAllCourse();

    public void addCourse(Course course);

    public Course selectById(String no);

    public void updateCourse(Course course);

    public void deleteById(String no);

    public List<Course> selectCourseTable(String sno);
}
