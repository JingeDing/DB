package com.example.springboot.dao;

import com.example.springboot.pojo.Class;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ClassDao {

    public List<Class> selectAllClass();

    public void addClass(Class classItem);

    public Class selectById(String cno);

    public void updateClass(Class classItem);

    public void deleteById(String cno);

}
