package com.example.springboot.service;

import com.example.springboot.pojo.Class;
import com.example.springboot.pojo.Department;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassService {

    PageInfo<Class> selectAllClass(int pageNum, int pageSize);

    public void addClass(Class classItem);

    public Class selectById(String cno);

    public void updateClass(Class classItem);

    public void deleteById(String cno);
}
