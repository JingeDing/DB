package com.example.springboot.service;

import com.example.springboot.dao.CourseDao;
import com.example.springboot.pojo.Course;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public PageInfo<Course> selectAllCourse(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseDao.selectAllCourse();
        for (Course item : list) {
            switch (item.getWeekday()) {
                case "1":
                    item.setWeekday("周一");
                    break;
                case "2":
                    item.setWeekday("周二");
                    break;
                case "3":
                    item.setWeekday("周三");
                    break;
                case "4":
                    item.setWeekday("周四");
                    break;
                case "5":
                    item.setWeekday("周五");
                    break;
                case "6":
                    item.setWeekday("周六");
                    break;
                case "7":
                    item.setWeekday("周日");
                    break;
            }
            switch (item.getCtime()) {
                case "1":
                    item.setCtime("08:00-09:40");
                    break;
                case "2":
                    item.setCtime("10:10-11:50");
                    break;
                case "3":
                    item.setCtime("14:00-15:40");
                    break;
                case "4":
                    item.setCtime("16:00-17:40");
                    break;
                case "5":
                    item.setCtime("19:00-20:40");
                    break;
            }
        }
        return new PageInfo<Course>(list);
    }

    @Override
    public void addCourse(Course course) {
        courseDao.addCourse(course);
    }

    @Override
    public Course selectById(String no) {
        return courseDao.selectById(no);
    }

    @Override
    public void update(Course course) {
        courseDao.updateCourse(course);
    }

    @Override
    public void deleteById(String no) {
        courseDao.deleteById(no);
    }

    @Override
    public List<Course> selectCourseTable(String sno) {
        return courseDao.selectCourseTable(sno);
    }
}
