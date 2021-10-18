package com.example.springboot.service;

import com.example.springboot.dao.ClassDao;
import com.example.springboot.pojo.Class;
import com.example.springboot.pojo.Department;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao cd;

    @Override
    public PageInfo<Class> selectAllClass(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Class> list = cd.selectAllClass();
        return new PageInfo<Class>(list);
    }

    @Override
    public void addClass(Class classItem) {
        cd.addClass(classItem);
    }

    @Override
    public Class selectById(String cno) {
        return cd.selectById(cno);
    }

    @Override
    public void updateClass(Class classItem) {
        cd.updateClass(classItem);
    }

    @Override
    public void deleteById(String cno) {
        cd.deleteById(cno);
    }

}
