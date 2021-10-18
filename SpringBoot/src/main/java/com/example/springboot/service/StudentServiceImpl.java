package com.example.springboot.service;

import com.example.springboot.dao.StudentDao;
import com.example.springboot.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao sd;

    @Override
    public List<Student> queryAll() {
        return sd.queryAll();
    }

    @Override
    public Student queryById(String id) {
        return sd.queryById(id);
    }

    @Override
    public PageInfo<Student> studentPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = sd.selectAll();
        return new PageInfo<Student>(list);
    }

    @Override
    public void addStudent(Student student) {
        sd.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        sd.updateStudent(student);
    }

    @Override
    public void deleteById(String sno) {
        sd.deleteById(sno);
    }

    @Override
    public List<Student> selectByCno(String cno) {
        return sd.selectByCno(cno);
    }
}
