package com.example.springboot.service;

import com.example.springboot.dao.TeacherDao;
import com.example.springboot.pojo.Teacher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao td;

    @Override
    public PageInfo<Teacher> selectAllTeacher(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = td.selectAllTeacher();
        return new PageInfo<Teacher>(list);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        td.addTeacher(teacher);
    }

    @Override
    public Teacher selectById(String tno) {
        return td.selectById(tno);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        td.updateTeacher(teacher);
    }

    @Override
    public void deleteById(String tno) {
        td.deleteById(tno);
    }

    @Override
    public List<Teacher> selectBySno(String sno) {
        return td.selectBySno(sno);
    }
}
